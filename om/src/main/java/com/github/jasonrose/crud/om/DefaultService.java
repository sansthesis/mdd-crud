package com.github.jasonrose.crud.om;

import java.util.List;

import com.github.jasonrose.crud.security.Authorizer;
import com.github.jasonrose.crud.security.Authorizer.Operation;
import com.github.jasonrose.crud.validation.Validator;

/**
 * This default service implementation performs authorization checks and validation checks if necessary before delegating to the dao.
 * @author Jason Rose
 * 
 * @param <E> The entity type that this service services.
 * @param <D> The dao type that accesses data for the entity type.
 * @param <A> The authorizer type that authorizes operations for the given entity type.
 * @param <V> The validator type that validates creation/updates for the given entity type.
 */
public class DefaultService<E extends AbstractEntity, D extends Dao<E>, A extends Authorizer<E>, V extends Validator<E>> implements Service<E> {

  protected final D dao;
  protected final A authorizer;
  protected final V validator;

  public DefaultService(final D dao, final A authorizer, final V validator) {
    this.dao = dao;
    this.authorizer = authorizer;
    this.validator = validator;
  }

  @Override
  public E create(final E entity) {
    authorizer.authorize(Operation.CREATE, entity);
    validator.validate(entity);
    return dao.create(entity);
  }

  @Override
  public void delete(final long id) {
    authorizer.authorize(Operation.DELETE, id);
    dao.delete(id);
  }

  @Override
  public E get(final long id) {
    authorizer.authorize(Operation.READ, id);
    return dao.get(id);
  }

  @Override
  public List<E> list() {
    authorizer.authorize(Operation.READ);
    return dao.list();
  }

  @Override
  public E update(final long id, final E entity) {
    authorizer.authorize(Operation.UPDATE, entity);
    validator.validate(entity);
    return dao.update(entity);
  }
}
