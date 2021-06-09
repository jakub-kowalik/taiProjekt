package com.example.taiprojekt.order;

import com.example.taiprojekt.entities.LineItemEntity;
import com.example.taiprojekt.entities.OrderEntity;
import com.example.taiprojekt.lineitem.LineItemRequest;
import com.example.taiprojekt.lineitem.LineItemService;
import com.example.taiprojekt.order.dtos.OrderFactory;
import com.example.taiprojekt.order.dtos.OrderRequest;
import com.example.taiprojekt.order.dtos.OrderResponse;
import com.example.taiprojekt.order.exceptions.OrderNotFoundException;
import com.example.taiprojekt.product.ProductRepository;
import com.example.taiprojekt.product.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final LineItemService lineItemService;

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

    @Transactional
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        OrderEntity orderEntity = OrderEntity.builder()
                .name(orderRequest.getName())
                .build();

        List<LineItemEntity> productEntities = new ArrayList<>();

        if (orderRequest.getProduct() != null)
            for (LineItemRequest lineItemRequest : orderRequest.getProduct()) {
               productEntities.add(
                       LineItemEntity.builder()
                               .quantity(lineItemRequest.getQuantity())
                               .product( productRepository.findById(lineItemRequest.getProductId())
                                       .orElseThrow(() -> new ProductNotFoundException(lineItemRequest.getProductId())))
                               .order(orderEntity)
                               .build()
               );
            }

        orderEntity.setProducts(productEntities);

        return OrderFactory.orderEntityToResponse(orderRepository.save(orderEntity));
    }

    @Transactional
    public OrderResponse modifyOrder(Long id, OrderRequest orderRequest) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        if (orderRequest.getProduct() != null) {
            List<LineItemEntity> productEntities = new ArrayList<>();

            lineItemService.deleteLineItems(orderEntity.getProducts());

            for (LineItemRequest lineItemRequest : orderRequest.getProduct()) {
                productEntities.add(
                        LineItemEntity.builder()
                                .quantity(lineItemRequest.getQuantity())
                                .product( productRepository.findById(lineItemRequest.getProductId())
                                        .orElseThrow(() -> new ProductNotFoundException(lineItemRequest.getProductId())))
                                .order(orderEntity)
                                .build()
                );
            }

            orderEntity.setProducts(productEntities);
        }

        return OrderFactory.orderEntityToResponse(orderRepository.save(orderEntity));
    }

    @Transactional
    public void deleteOrder(Long id) {
        lineItemService.deleteLineItems(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id)).getProducts());
        orderRepository.deleteById(id);
    }
}
