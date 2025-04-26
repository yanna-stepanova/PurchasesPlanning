package com.household.purchases.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String entity, Object id) {
    super(entity + " with id " + id + " not found");
  }
}
