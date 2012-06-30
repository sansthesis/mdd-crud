package com.github.jasonrose.crud.om;

import java.util.List;

/**
 * It's back.
 * @author Jason Rose
 * 
 * @param <E> Preferably an entity type, following javabean conventions.
 */
public interface Service<E> {

  /**
   * Create.
   * @param source The entity that you want to create.
   * @return The entity that was created.
   */
  E create(E source);

  /**
   * Delete.
   * @param id The id of the entity that you want to delete.
   */
  void delete(long id);

  /**
   * Read.
   * @param id The id of the entity that you want to read.
   * @return The entity relating to the specified id.
   */
  E get(long id);

  /**
   * List.
   * @return A list of entities of this instance's parameterized type.
   */
  List<E> list();

  /**
   * Update.
   * @param id The id of the entity that you want to update.
   * @param source The entity that you want to persist.
   * @return The entity that was updated.
   */
  E update(long id, E source);
}
