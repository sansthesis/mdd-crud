package com.github.jasonrose.crud.om;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.github.jasonrose.crud.om.AbstractFinder.Pred;
import com.google.inject.TypeLiteral;
import com.google.inject.persist.Transactional;

public class DefaultDao<E extends AbstractEntity> implements FluentDao<E> {

  protected final Class<E> entityClass;
  protected final EntityManager em;

  protected DefaultDao(final Class<E> entityClass, final EntityManager em) {
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
  public <D extends FluentDao<E>, F extends AbstractFinder<E, D, F>> AbstractFinder<E, D, F> finder() {
    throw new UnsupportedOperationException("No default implementation for finder.");
  }

  @Override
  @Transactional
  public E get(final Long id) {
    return em.find(entityClass, id);
  }

  @Override
  @Transactional
  public E get(final Map<String, Pred<?>> context) {
    return toCriteria(context).getSingleResult();
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
  public List<E> list(final Map<String, Pred<?>> context) {
    return toCriteria(context).getResultList();
  }

  @Override
  @Transactional
  public E update(final E entity) {
    create(entity);
    return entity;
  }

  protected TypedQuery<E> toCriteria(final Map<String, Pred<?>> context) {
    final CriteriaBuilder qb = em.getCriteriaBuilder();
    final CriteriaQuery<E> c = qb.createQuery(entityClass);
    final Root<E> p = c.from(entityClass);
    Predicate condition = qb.conjunction();
    for( final Map.Entry<String, Pred<?>> entry : context.entrySet() ) {
      condition = qb.and(condition, entry.getValue().toExpression((Path) p.get(entry.getKey()), qb));
    }
    return em.createQuery(c.where(condition));
  }
}
