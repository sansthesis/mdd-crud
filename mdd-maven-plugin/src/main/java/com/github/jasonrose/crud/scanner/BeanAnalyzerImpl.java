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

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

public class BeanAnalyzerImpl implements BeanAnalyzer {
  private static final Set<Class<? extends Annotation>> RELATIONSHIP_ANNOTATIONS = ImmutableSet.of(ManyToMany.class, OneToMany.class, ManyToOne.class, OneToOne.class);

  private static final Function<Annotation, Class<? extends Annotation>> TRANSFORM_ANNOTATION_TO_CLASS = new Function<Annotation, Class<? extends Annotation>>() {
    @Override
    public Class<? extends Annotation> apply(final Annotation input) {
      Preconditions.checkNotNull(input);
      return input.annotationType();
    }
  };

  @Override
  public Predicate<PropertyDescriptor> isDesirableProperty() {
    return new Predicate<PropertyDescriptor>() {
      @Override
      public boolean apply(final PropertyDescriptor input) {
        return isDesirableProperty(input);
      }
    };
  }

  @Override
  public boolean isDesirableProperty(final PropertyDescriptor descriptor) {
    return descriptor != null && descriptor.getWriteMethod() != null && descriptor.getReadMethod() != null;
  }

  @Override
  public Predicate<PropertyDescriptor> isRelationship() {
    return new Predicate<PropertyDescriptor>() {
      @Override
      public boolean apply(final PropertyDescriptor input) {
        return isRelationship(input);
      }
    };
  }

  @Override
  public boolean isRelationship(final PropertyDescriptor descriptor) {
    boolean isValid = false;
    if( descriptor != null ) {
      final Collection<Class<? extends Annotation>> annotations = Lists.newArrayList(Lists.transform(Arrays.asList(descriptor.getReadMethod().getAnnotations()), TRANSFORM_ANNOTATION_TO_CLASS));
      isValid = annotations.removeAll(RELATIONSHIP_ANNOTATIONS);
    }
    return isValid;
  }

  @Override
  public Predicate<PropertyDescriptor> isSimpleProperty() {
    return Predicates.not(isRelationship());
  }

  @Override
  public boolean isSimpleProperty(final PropertyDescriptor descriptor) {
    return !isRelationship(descriptor);
  }
}
