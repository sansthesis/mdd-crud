package com.github.jasonrose.crud.validation.spi;

import com.github.jasonrose.crud.validation.Validator;

/**
 * This class is an implementation of Validator that allows everything.
 * @author Jason Rose
 * 
 */
public class NoOpValidatorImpl implements Validator<Object> {

  @Override
  public void validate(final Object entity) {
  }
}
