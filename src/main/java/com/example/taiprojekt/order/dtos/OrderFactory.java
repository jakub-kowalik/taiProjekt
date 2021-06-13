package com.example.taiprojekt.order.dtos;

import com.example.taiprojekt.entities.OrderEntity;
import com.example.taiprojekt.lineitem.dtos.LineItemFactory;

import java.util.stream.Collectors;

public class OrderFactory {

/*    public static OrderEntity orderRequestToEntity(OrderRequest) {

    }*/

    public static OrderResponse orderEntityToResponse(OrderEntity orderEntity) {
        return OrderResponse.builder()
                .id(orderEntity.getId())
                .name(orderEntity.getName())
                .date(orderEntity.getDate())
                .products(orderEntity.getProducts()
                                .stream()
                                .map(LineItemFactory::lineItemToResponse)
                                .collect(Collectors.toList()))
                .build();
    }
}
