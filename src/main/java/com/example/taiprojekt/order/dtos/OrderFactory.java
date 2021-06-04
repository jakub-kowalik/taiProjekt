package com.example.taiprojekt.order.dtos;

import com.example.taiprojekt.order.OrderEntity;
import com.example.taiprojekt.product.dtos.ProductFactory;

import java.util.stream.Collectors;

public class OrderFactory {

/*    public static OrderEntity orderRequestToEntity(OrderRequest) {

    }*/

    public static OrderResponse orderEntityToResponse(OrderEntity orderEntity) {
        return OrderResponse.builder()
                .id(orderEntity.getId())
                .name(orderEntity.getName())
                .products(orderEntity.getProducts()
                                .stream()
                                .map(ProductFactory::productEntityToResponse)
                                .collect(Collectors.toList()))
                .build();
    }
}