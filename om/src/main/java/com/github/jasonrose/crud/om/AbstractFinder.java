package com.github.jasonrose.crud.om;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public abstract class AbstractFinder<T, D extends FluentDao<T>, F extends AbstractFinder<T, D, F>> {
  protected final Map<String, Object> context;
  protected final D dao;

  public AbstractFinder(final D dao) {
    context = Maps.newHashMap();
    this.dao = dao;
  }

  public T get() {
    return dao.get(context);
  }

  public List<T> list() {
    return dao.list(context);
  }

  // http://egalluzzo.blogspot.com/2010/06/using-inheritance-with-fluent.html
  protected abstract F getThis();

  protected F helper(final String key, final Object value) {
    context.put(key, value);
    return getThis();
  }
}