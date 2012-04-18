package com.github.jasonrose.crud.om;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.PropertyDescriptor;

import com.google.common.collect.Sets;

public class NoOpValidator implements Validator {

  @Override
  public <T> Set<ConstraintViolation<T>> validate(final T object, final Class<?>... groups) {
    return Sets.newHashSet();
  }

  @Override
  public <T> Set<ConstraintViolation<T>> validateProperty(final T object, final String propertyName, final Class<?>... groups) {
    return Sets.newHashSet();
  }

  @Override
  public <T> Set<ConstraintViolation<T>> validateValue(final Class<T> beanType, final String propertyName, final Object value, final Class<?>... groups) {
    return Sets.newHashSet();
  }

  @Override
  public BeanDescriptor getConstraintsForClass(final Class<?> clazz) {
    return new BeanDescriptor() {

      @Override
      public boolean hasConstraints() {
        return false;
      }

      @Override
      public Class<?> getElementClass() {
        return null;
      }

      @Override
      public Set<ConstraintDescriptor<?>> getConstraintDescriptors() {
        return null;
      }

      @Override
      public ConstraintFinder findConstraints() {
        return null;
      }

      @Override
      public boolean isBeanConstrained() {
        return false;
      }

      @Override
      public PropertyDescriptor getConstraintsForProperty(final String propertyName) {
        return null;
      }

      @Override
      public Set<PropertyDescriptor> getConstrainedProperties() {
        return null;
      }
    };
  }

  @Override
  public <T> T unwrap(final Class<T> type) {
    throw new ValidationException("Unsupported");
  }

}
