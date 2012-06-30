package com.github.jasonrose.crud.security.spi;

import com.github.jasonrose.crud.security.Authorizer;

/**
 * This class is a default implementation of the Authorizer interface that allows anything.
 * @author Jason Rose
 * 
 * @param <T> Any type
 */
public class NoOpAuthorizerImpl<T> implements Authorizer<T> {

  @Override
  public void authorize(final Operation operation) {
  }

  @Override
  public void authorize(final Operation operation, final long id) {
  }

  @Override
  public void authorize(final Operation operation, final T entity) {
  }

}
