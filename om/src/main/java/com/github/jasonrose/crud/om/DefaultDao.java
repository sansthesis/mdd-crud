package com.github.jasonrose.crud.om;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.persist.Transactional;

public class DefaultDao<E extends AbstractEntity> implements Dao<E> {

  private final Class<? extends E> entityClass;

  @Inject
  protected EntityManager em;

  public DefaultDao(final Class<? extends E> entityClass) {
    this.entityClass = entityClass;
  }

  @Override
  @Transactional
  public E create(final E entity) {
    em.persist(entity);
    return entity;
  }

  @Override
  @Transactional
  public boolean delete(final Long id) {
    em.remove(get(id));
    return true;
  }

  @Override
  @Transactional
  public E get(final Long id) {
    return em.find(entityClass, id);
  }

  @Override
  @Transactional
  public Set<E> getByManyRelationship(final String relationshipName, final Long id) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  @Transactional
  public E getByOneRelationship(final String relationshipName, final Long id) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @SuppressWarnings("unchecked")
  @Override
  @Transactional
  public List<E> list() {
    final String selectQueryString = String.format("from %s", entityClass.getName());
    return em.createQuery(selectQueryString).getResultList();
  }

  @Override
  @Transactional
  public E update(final E entity) {
    create(entity);
    return entity;
  }
}