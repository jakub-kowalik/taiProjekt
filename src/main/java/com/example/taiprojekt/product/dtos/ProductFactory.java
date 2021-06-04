package com.example.taiprojekt.product.dtos;

import com.example.taiprojekt.product.ProductEntity;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductFactory {

    public static ProductEntity productRequestToEntity(ProductRequest productRequest) {
        return ProductEntity.builder()
                .name(productRequest.getName())
                .build();
    }

    public static ProductResponse productEntityToResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .build();
    }
}
