package com.github.jasonrose.crud.scanner.stubs;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.praxissoftware.rest.core.AbstractMapEntity;

public class DefaultDao<E extends AbstractMapEntity> implements Dao<E> {
  private final Map<Long, E> map = Maps.newHashMap();
  private final Validator validator;

  public DefaultDao(final Validator validator) {
    this.validator = validator;
  }

  @Override
  public E create(final E entity) {
    validate(validator, entity);
    final E mapEntity = map.get(entity.get("id"));
    if( mapEntity != null ) {
      throw new IllegalStateException("Entity with id " + entity.get("id") + " already exists!");
    }
    return map.put((Long) entity.get("id"), entity);
  }

  @Override
  public boolean delete(final Long id) {
    return map.remove(id) != null;
  }

  @Override
  public E get(final Long id) {
    return map.get(id);
  }

  @Override
  public List<E> list() {
    return Lists.newArrayList(map.values());
  }

  @Override
  public E update(final E entity) {
    validate(validator, entity);
    final E mapEntity = map.get(entity.get("id"));
    if( mapEntity == null ) {
      throw new IllegalStateException("Entity with id " + entity.get("id") + " doesn't exist!");
    }
    return map.put((Long) entity.get("id"), entity);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected boolean validate(final Validator v, final E entity) {
    final Set<ConstraintViolation<E>> violations = v.validate(entity, new Class[0]);
    if( violations.size() > 0 ) {
      throw new ConstraintViolationException(new HashSet(violations));
    }
    return true;
  }
}