package com.github.jasonrose.crud.scanner;

import java.beans.PropertyDescriptor;

import com.google.common.base.Predicate;

/**
 * This service helps process bean properties.
 * @author Jason Rose
 */
public interface BeanAnalyzer {
  /**
   * Desirable properties have a getter and a setter.
   * @return
   */
  Predicate<PropertyDescriptor> isDesirableProperty();

  /**
   * Desirable properties have a getter and a setter.
   * @param descriptor
   * @return
   */
  boolean isDesirableProperty(PropertyDescriptor descriptor);

  /**
   * Relationship properties are annotated with a JPA relationship annotation.
   * @return
   */
  Predicate<PropertyDescriptor> isRelationship();

  /**
   * Relationship properties are annotated with a JPA relationship annotation.
   * @param descriptor
   * @return
   */
  boolean isRelationship(PropertyDescriptor descriptor);

  /**
   * Simple properties are not annotated with a JPA relationship annotation.
   * @return
   */
  Predicate<PropertyDescriptor> isSimpleProperty();

  /**
   * Simple properties are not annotated with a JPA relationship annotation.
   * @param descriptor
   * @return
   */
  boolean isSimpleProperty(PropertyDescriptor descriptor);
}
