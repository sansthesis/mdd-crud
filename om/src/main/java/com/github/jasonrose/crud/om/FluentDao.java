package com.github.jasonrose.crud.om;

import java.util.List;
import java.util.Map;

import com.github.jasonrose.crud.om.AbstractFinder.Pred;

public abstract interface FluentDao<E> extends Dao<E> {

  <D extends FluentDao<E>, F extends AbstractFinder<E, D, F>> AbstractFinder<E, D, F> finder();

  E get(Map<String, Pred<?>> context);

  List<E> list(Map<String, Pred<?>> context);
}
