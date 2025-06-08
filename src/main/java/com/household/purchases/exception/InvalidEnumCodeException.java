package com.household.purchases.exception;

public class InvalidEnumCodeException extends RuntimeException {
    public InvalidEnumCodeException(Class<?> enumClass, String invalidCode) {
        super("Invalid value for enum " + enumClass.getSimpleName() + ": '" + invalidCode + "'");
    }
}
