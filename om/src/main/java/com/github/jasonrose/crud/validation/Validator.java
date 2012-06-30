package com.github.jasonrose.crud.validation;

/**
 * The validator is an abstraction for validating entities regardless of implementation used.
 * @author Jason Rose
 * 
 * @param <E> Preferably an entity type, following javabean conventions.
 */
public interface Validator<E> {

  /**
   * Validates the entity.
   * @param entity The object to validate.
   */
  void validate(E entity);
}
