package com.example.taiprojekt.lineitem;

import com.example.taiprojekt.entities.LineItemEntity;
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
