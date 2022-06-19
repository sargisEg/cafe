package com.cafe.service.impl.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long orderId) {
        super("Not found order with id - " + orderId);
    }
}
