package com.cafe.service.impl.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Not found product with id - " + productId);
    }
}
