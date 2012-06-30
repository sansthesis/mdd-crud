package com.github.jasonrose.crud.scanner;

import java.util.Collection;

/**
 * Emitters operate on Models and convert them into Emissions of generated source files.
 * @author Jason Rose
 */
public interface Emitter {
  /**
   * Generates an Emission for a collection of Models.
   * @param models Models to convert into a generated source file.
   * @return An Emission of generated source.
   */
  Emission emit(Collection<Model> models);

  /**
   * Generates an Emission for a single Model.
   * @param model The Model to convert into a generated source file.
   * @return An Emission of generated source.
   */
  Emission emit(Model model);
}
