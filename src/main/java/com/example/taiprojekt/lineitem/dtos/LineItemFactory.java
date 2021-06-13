package com.example.taiprojekt.lineitem.dtos;

import com.example.taiprojekt.entities.LineItemEntity;
import com.example.taiprojekt.lineitem.dtos.LineItemResponse;
import com.example.taiprojekt.product.dtos.ProductFactory;

public class LineItemFactory {

    public static LineItemResponse lineItemToResponse(LineItemEntity lineItemEntity) {
        return LineItemResponse.builder()
                .id(lineItemEntity.getId())
                .product(ProductFactory.productEntityToResponse(lineItemEntity.getProduct()))
                .quantity(lineItemEntity.getQuantity())
                .build();
    }
}
