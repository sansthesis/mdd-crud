package com.github.jasonrose.crud.validation.spi;

import org.junit.Before;
import org.junit.Test;

public class NoOpValidatorImplTest {
  private NoOpValidatorImpl service;

  @Before
  public void setUp() {
    service = new NoOpValidatorImpl();
  }

  @Test
  public void testValidateEntity() {
    service.validate(new Object());
  }

  @Test
  public void testValidateEntityAllowsNull() {
    service.validate(null);
  }
}
