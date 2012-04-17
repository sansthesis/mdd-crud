package com.github.jasonrose.crud.scanner;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.beanutils.PropertyUtilsBean;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ClassScanner {
  private final Predicate<PropertyDescriptor> desirablePropertyPredicate = new Predicate<PropertyDescriptor>() {
    @Override
    public boolean apply(final PropertyDescriptor input) {
      return (input.getWriteMethod() != null) && (input.getReadMethod() != null);
    }
  };

  private final Predicate<PropertyDescriptor> simplePropertyPredicate = new Predicate<PropertyDescriptor>() {
    final Set<Class<? extends Annotation>> relationshipAnnotations = ImmutableSet.of(ManyToMany.class, OneToMany.class, ManyToOne.class, OneToOne.class);

    @Override
    public boolean apply(final PropertyDescriptor input) {
      final Collection<Class<? extends Annotation>> annotations = Lists.newArrayList(Lists.transform(Arrays.asList(input.getReadMethod().getAnnotations()), transformAnnotationToClass));
      return !annotations.removeAll(this.relationshipAnnotations);
    }
  };

  private final Function<Annotation, Class<? extends Annotation>> transformAnnotationToClass = new Function<Annotation, Class<? extends Annotation>>() {
    @Override
    public Class<? extends Annotation> apply(final Annotation input) {
      return input.annotationType();
    }
  };

  public Model generateModel(final Class<?> entityClass) {
    final Model model = new Model();
    model.setEntityClass(entityClass);
    model.setEntityClassName(entityClass.getName());
    model.setEntityClassSimpleName(entityClass.getSimpleName());
    model.setEntityClassPackageName(entityClass.getPackage().getName());

    final PropertyUtilsBean propertyUtils = new PropertyUtilsBean();
    final Set<PropertyDescriptor> rawDescriptors = ImmutableSet.copyOf(Arrays.asList(propertyUtils.getPropertyDescriptors(entityClass)));
    final Set<PropertyDescriptor> descriptors = Sets.filter(rawDescriptors, desirablePropertyPredicate);
    final Set<PropertyDescriptor> properties = Sets.filter(descriptors, simplePropertyPredicate);
    final Set<PropertyDescriptor> relationships = Sets.filter(descriptors, Predicates.not(simplePropertyPredicate));
    model.setProperties(properties);
    model.setRelationships(relationships);
    return model;
  }
}