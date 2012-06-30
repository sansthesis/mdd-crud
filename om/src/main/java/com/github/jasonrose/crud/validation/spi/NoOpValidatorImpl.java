package com.github.jasonrose.crud.validation.spi;

import com.github.jasonrose.crud.validation.Validator;

/**
 * This class is an implementation of Validator that allows everything.
 * @author Jason Rose
 * 
 * @param <T> Any type.
 * 
 */
public class NoOpValidatorImpl<T> implements Validator<T> {

  @Override
  public void validate(final T entity) {
  }
}
