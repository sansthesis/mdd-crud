package com.github.jasonrose.crud.scanner;

import java.beans.PropertyDescriptor;
import java.util.Set;

import com.google.common.collect.Sets;

public class Model {
  private Class<?> entityClass;
  private String entityClassName;
  private String entityClassSimpleName;
  private String entityClassPackageName;
  private Set<PropertyDescriptor> relationships = Sets.newHashSet();
  private Set<PropertyDescriptor> properties = Sets.newHashSet();

  public Class<?> getEntityClass() {
    return this.entityClass;
  }

  public String getEntityClassName() {
    return this.entityClassName;
  }

  public String getEntityClassPackageName() {
    return this.entityClassPackageName;
  }

  public String getEntityClassSimpleName() {
    return this.entityClassSimpleName;
  }

  public Set<PropertyDescriptor> getProperties() {
    return this.properties;
  }

  public Set<PropertyDescriptor> getRelationships() {
    return this.relationships;
  }

  public void setEntityClass(final Class<?> entityClass) {
    this.entityClass = entityClass;
  }

  public void setEntityClassName(final String entityClassName) {
    this.entityClassName = entityClassName;
  }

  public void setEntityClassPackageName(final String entityClassPackageName) {
    this.entityClassPackageName = entityClassPackageName;
  }

  public void setEntityClassSimpleName(final String entityClassSimpleName) {
    this.entityClassSimpleName = entityClassSimpleName;
  }

  public void setProperties(final Set<PropertyDescriptor> properties) {
    this.properties = properties;
  }

  public void setRelationships(final Set<PropertyDescriptor> relationships) {
    this.relationships = relationships;
  }
}