package com.github.jasonrose.crud.scanner;

import java.util.Collection;

public interface Emitter {
  Emission emit(Model model);

  Emission emit(Collection<Model> models);
}
