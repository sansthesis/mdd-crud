package com.github.jasonrose.crud.security;

/**
 * The authorizer is responsible for permissions checks for CRUD operations on entities.
 * @author Jason Rose
 * 
 * @param <E> Preferably an entity type, following javabean conventions.
 */
public interface Authorizer<E> {

  /**
   * An enumeration of valid operations to perform regarding entities.
   * @author Jason Rose
   * 
   */
  enum Operation {
    CREATE, READ, UPDATE, DELETE
  }

  /**
   * For example, authorizing the listing of a specific entity type.
   * @param operation The operation to perform.
   */
  void authorize(Operation operation);

  /**
   * For example, authorizing the deletion of a specific entity.
   * @param operation The operation to perform.
   * @param entity The entity to use for contextual authorization.
   */
  void authorize(Operation operation, E entity);

  /**
   * For example, authorizing the reading of an entity with the specified id.
   * @param operation The operation to perform.
   * @param id The id of the entity that we want to perform the operation on.
   */
  void authorize(Operation operation, long id);
}
