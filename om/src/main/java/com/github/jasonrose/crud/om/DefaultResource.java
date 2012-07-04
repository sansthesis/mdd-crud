package com.github.jasonrose.crud.om;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.github.jasonrose.crud.representation.Representer;
import com.praxissoftware.rest.core.Representation;

/**
 * This is a simple implementation of a Jersey resource that delegates to a service for CRUD operations.
 * @author Jason Rose
 *
 * @param <E> An entity type.
 * @param <S> A service for the given entity type.
 */
public class DefaultResource<E extends AbstractEntity, S extends Service<E>, R extends Representer<E>> {

  protected final S service;
  protected final R representer;

  @Inject
  protected DefaultResource(final S service, final R representer) {
    this.service = service;
    this.representer = representer;
  }

  public Response create(@Context final UriInfo uriInfo, final E entity) {
    final Representation output = representer.generateRepresentation(service.create(entity), getClass(), "get", uriInfo);
    return Response.ok(output).status(Status.CREATED).build();
  }

  public Response delete(@Context final UriInfo uriInfo, @PathParam("id") final Long id) {
    service.delete(id);
    return Response.status(Status.NO_CONTENT).build();
  }

  public Response get(@Context final UriInfo uriInfo, @PathParam("id") final Long id) {
    final Representation output = representer.generateRepresentation(service.get(id), getClass(), "get", uriInfo);
    return Response.ok(output).build();
  }

  public Response list(@Context final UriInfo uriInfo) {
    final Representation output = representer.generateListRepresentation(service.list(), getClass(), "list", "get", uriInfo);
    return Response.ok(output).build();
  }

  public Response update(@Context final UriInfo uriInfo, @PathParam("id") final Long id, final E entity) {
    final Representation output = representer.generateRepresentation(service.update(id, entity), getClass(), "get", uriInfo);
    return Response.ok(output).build();
  }
}
