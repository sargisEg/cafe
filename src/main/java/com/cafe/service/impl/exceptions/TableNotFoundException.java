package com.cafe.service.impl.exceptions;

public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException(Long tableId) {
        super("Not found table with id - " + tableId);
    }
}
