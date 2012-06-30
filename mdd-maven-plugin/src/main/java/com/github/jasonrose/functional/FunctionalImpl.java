package com.github.jasonrose.functional;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Functions;

public class FunctionalImpl implements Functional {

  @Override
  public <T> Function<Map<String, ? super T>, T> pluck(final String key) {
    return new Function<Map<String,? super T>, T>() {
      @SuppressWarnings("unchecked")
      @Override
      public T apply(Map<String, ? super T> input) {
        return (T) Functions.forMap(input, null).apply(key);
      }
    };
  }
}
