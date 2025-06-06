package com.household.purchases.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String entity, Object id) {
    super(entity + " with id " + id + " not found");
  }

  public NotFoundException(String entity, String field1, Object id1, String field2, Object id2) {
    super(String.format("%s with %s = %s and %s = %s", entity, field1, id1, field2, id2));
  }
}
