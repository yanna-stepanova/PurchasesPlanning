package com.household.purchases.exception;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String resourceObj, String fieldName, Object value) {
        super(String.format("%s with %s '%s' already exists", resourceObj, fieldName, value));
    }
}
