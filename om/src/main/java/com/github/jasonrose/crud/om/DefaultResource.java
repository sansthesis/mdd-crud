package com.github.jasonrose.crud.om;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * This is a simple implementation of a Jersey resource that delegates to a service for CRUD operations.
 * @author Jason Rose
 *
 * @param <E> An entity type.
 * @param <S> A service for the given entity type.
 */
public class DefaultResource<E extends AbstractEntity, S extends Service<E>> {

  protected final S service;

  @Inject
  protected DefaultResource(final S service) {
    this.service = service;
  }

  public Response create(@Context final UriInfo uriInfo, final E entity) {
    return Response.ok(service.create(entity)).status(Status.CREATED).build();
  }

  public Response delete(@Context final UriInfo uriInfo, @PathParam("id") final Long id) {
    service.delete(id);
    return Response.status(Status.NO_CONTENT).build();
  }

  public Response get(@Context final UriInfo uriInfo, @PathParam("id") final Long id) {
    return Response.ok(service.get(id)).build();
  }

  public Response list(@Context final UriInfo uriInfo) {
    return Response.ok(service.list()).build();
  }

  public Response update(@Context final UriInfo uriInfo, @PathParam("id") final Long id, final E entity) {
    return Response.ok(service.update(id, entity)).build();
  }
}
