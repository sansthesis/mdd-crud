package com.github.jasonrose.crud.security.spi;

import org.junit.Before;
import org.junit.Test;

import com.github.jasonrose.crud.security.Authorizer.Operation;

public class NoOpAuthorizerImplTest {

  private NoOpAuthorizerImpl<Object> service;
  
  @Before
  public void setUp() {
    service = new NoOpAuthorizerImpl<Object>();
  }
  
  @Test
  public void testAuthorizeEntityAllowsAllOperations() {
    for( final Operation operation : Operation.values() ) {
      service.authorize(operation, new Object());
    }
  }
  
  @Test
  public void testAuthorizeEntityAllowsAllOperationsOnNull() {
    for( final Operation operation : Operation.values() ) {
      service.authorize(operation, null);
    }
  }
  
  @Test
  public void testAuthorizeIdAllowsAllOperations() {
    for( final Operation operation : Operation.values() ) {
      service.authorize(operation, 1);
    }
  }
  
  @Test
  public void testAuthorizeAllowsAllOperations() {
    for( final Operation operation : Operation.values() ) {
      service.authorize(operation);
    }
  }
}
