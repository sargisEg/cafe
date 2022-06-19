package com.cafe.service.impl.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long waiterId) {
        super("Not found user with id " + waiterId);
    }
}
