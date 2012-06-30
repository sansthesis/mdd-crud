package com.github.jasonrose.crud.om;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.TypeLiteral;
import com.google.inject.persist.Transactional;

public class DefaultDao<E extends AbstractEntity> implements Dao<E> {

  protected final Class<? extends E> entityClass;
  protected final EntityManager em;
  
  protected DefaultDao(final Class<? extends E> entityClass, final EntityManager em) {
    this.entityClass = entityClass;
    this.em = em;    
  }

  @SuppressWarnings("unchecked")
  @Inject
  protected DefaultDao(final TypeLiteral<E> typeLiteral, final EntityManager em) {
    this((Class<E>) typeLiteral.getRawType(), em);
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
