package com.github.jasonrose.crud.om;

import java.util.List;

public abstract interface Dao<E> {
  E create(E entity);

  boolean delete(Long id);

  E get(Long id);

  List<E> list();

  E update(E entity);
}
