package com.github.jasonrose.crud.om;

import java.util.List;
import java.util.Set;

public abstract interface Dao<E> {
  E create(E entity);

  boolean delete(Long id);

  E get(Long id);

  Set<E> getByManyRelationship(String relationshipName, Long id);

  E getByOneRelationship(String relationshipName, Long id);

  List<E> list();

  E update(E entity);
}