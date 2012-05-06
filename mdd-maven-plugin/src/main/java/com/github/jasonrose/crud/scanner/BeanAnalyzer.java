package com.github.jasonrose.crud.scanner;

import java.beans.PropertyDescriptor;

import com.google.common.base.Predicate;

public interface BeanAnalyzer {
  boolean isDesirableProperty(PropertyDescriptor descriptor);
  Predicate<PropertyDescriptor> isDesirableProperty();
  Predicate<PropertyDescriptor> isRelationship();
  boolean isRelationship(PropertyDescriptor descriptor);
  Predicate<PropertyDescriptor> isSimpleProperty();
  boolean isSimpleProperty(PropertyDescriptor descriptor);
}
