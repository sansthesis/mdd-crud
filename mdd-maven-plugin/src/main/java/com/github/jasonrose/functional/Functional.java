package com.github.jasonrose.functional;

import java.util.Map;

import com.google.common.base.Function;

public interface Functional {

  /**
   * Same as underscore.js pluck.
   * @param key
   * @return
   */
  <T> Function<Map<String, ? super T>, T> pluck(String key);

}
