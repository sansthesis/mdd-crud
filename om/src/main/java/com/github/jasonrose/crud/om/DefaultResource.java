package com.github.jasonrose.crud.om;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
  public DefaultResource(final S service) {
    this.service = service;
  }

  @Path("")
  @POST
  @Produces({ MediaType.APPLICATION_JSON })
  @Consumes({ MediaType.APPLICATION_JSON })
  public Response create(@Context final UriInfo uriInfo, final E entity) {
    return Response.ok(service.create(entity)).status(Status.CREATED).build();
  }

  @Path("{id: \\d+}")
  @DELETE
  @Produces({ MediaType.APPLICATION_JSON })
  public Response delete(@Context final UriInfo uriInfo, @PathParam("id") final Long id) {
    service.delete(id);
    return Response.status(Status.NO_CONTENT).build();
  }

  @Path("{id: \\d+}")
  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response get(@Context final UriInfo uriInfo, @PathParam("id") final Long id) {
    return Response.ok(service.get(id)).build();
  }

  @Path("")
  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response list(@Context final UriInfo uriInfo) {
    return Response.ok(service.list()).build();
  }

  @Path("{id: \\d+}")
  @PUT
  @Produces({ MediaType.APPLICATION_JSON })
  @Consumes({ MediaType.APPLICATION_JSON })
  public Response update(@Context final UriInfo uriInfo, @PathParam("id") final Long id, final E entity) {
    return Response.ok(service.update(id, entity)).build();
  }
}
