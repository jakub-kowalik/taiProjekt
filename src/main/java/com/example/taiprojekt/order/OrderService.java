package com.example.taiprojekt.order;

import com.example.taiprojekt.entities.OrderEntity;
import com.example.taiprojekt.order.dtos.OrderFactory;
import com.example.taiprojekt.order.dtos.OrderRequest;
import com.example.taiprojekt.order.dtos.OrderResponse;
import com.example.taiprojekt.order.exceptions.OrderNotFoundException;
import com.example.taiprojekt.entities.ProductEntity;
import com.example.taiprojekt.product.ProductRepository;
import com.example.taiprojekt.product.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderFactory::orderEntityToResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrder(Long id) {
        return OrderFactory.orderEntityToResponse(
                orderRepository.findById(id)
                        .orElseThrow(() -> new OrderNotFoundException(id))
        );
    }

    public OrderResponse saveOrder(OrderRequest orderRequest) {
        OrderEntity orderEntity = OrderEntity.builder()
                .name(orderRequest.getName())
                .build();

        List<ProductEntity> productEntities = new ArrayList<>();

        if (orderRequest.getProductIds() != null)
            for (Long l : orderRequest.getProductIds()) {
                productEntities.add(productRepository.findById(l)
                        .orElseThrow(() -> new ProductNotFoundException(l)));
            }

        orderEntity.setProducts(productEntities);

        return OrderFactory.orderEntityToResponse(orderRepository.save(orderEntity));
    }

    public OrderResponse modifyOrder(Long id, OrderRequest orderRequest) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        if (orderRequest.getProductIds() != null) {
            List<ProductEntity> productEntities = new ArrayList<>();

            for (Long l : orderRequest.getProductIds()) {
                productEntities.add(productRepository.findById(l)
                        .orElseThrow(() -> new ProductNotFoundException(l)));
            }

            orderEntity.setProducts(productEntities);
        }

        return OrderFactory.orderEntityToResponse(orderRepository.save(orderEntity));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
