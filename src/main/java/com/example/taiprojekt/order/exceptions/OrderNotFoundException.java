package com.example.taiprojekt.order.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long orderId) {
        super("Order for id =" + orderId + " not found!");
    }
}
