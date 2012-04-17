package com.github.jasonrose.crud.scanner.stubs;

import java.util.List;

import com.github.jasonrose.crud.om.AbstractEntity;

public abstract interface Dao<E extends AbstractEntity> {
  E create(E paramE);

  boolean delete(E paramE);

  boolean delete(Long paramLong);

  E get(Long paramLong);

  List<E> list();

  E update(E paramE);
}