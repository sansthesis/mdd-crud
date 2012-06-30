package com.github.jasonrose.crud.scanner;

import java.beans.PropertyDescriptor;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtilsBean;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class ClassScanner {

  private final BeanAnalyzer beanAnalyzer;

  public ClassScanner(final BeanAnalyzer beanAnalyzer) {
    this.beanAnalyzer = beanAnalyzer;
  }

  public Model generateModel(final Class<?> entityClass) {
    final Model model = new Model();
    model.setEntityClass(entityClass);
    model.setEntityClassName(entityClass.getName());
    model.setEntityClassSimpleName(entityClass.getSimpleName());
    model.setEntityClassPackageName(entityClass.getPackage().getName());

    final PropertyUtilsBean propertyUtils = new PropertyUtilsBean();
    final Set<PropertyDescriptor> rawDescriptors = ImmutableSet.copyOf(propertyUtils.getPropertyDescriptors(entityClass));
    final Set<PropertyDescriptor> descriptors = Sets.filter(rawDescriptors, beanAnalyzer.isDesirableProperty());
    final Set<PropertyDescriptor> properties = Sets.filter(descriptors, beanAnalyzer.isSimpleProperty());
    final Set<PropertyDescriptor> relationships = Sets.filter(descriptors, beanAnalyzer.isRelationship());
    model.setProperties(properties);
    model.setRelationships(relationships);
    return model;
  }
}
