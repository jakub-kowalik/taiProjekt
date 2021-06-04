package com.example.taiprojekt.product.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Product for id =" + productId + " not found!");
    }
}
