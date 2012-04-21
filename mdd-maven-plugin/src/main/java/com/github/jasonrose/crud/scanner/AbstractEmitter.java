package com.github.jasonrose.crud.scanner;

import java.util.Collection;

public abstract class AbstractEmitter implements Emitter {

  @Override
  public Emission emit(final Model model) {
    return null;
  }

  @Override
  public Emission emit(final Collection<Model> models) {
    return null;
  }

}
