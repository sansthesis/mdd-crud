package com.github.jasonrose.crud.scanner.stubs;

import java.util.List;

public abstract interface Dao<E> {
  E create(E e);

  boolean delete(Long id);

  E get(Long paramLong);

  List<E> list();

  E update(E paramE);
}