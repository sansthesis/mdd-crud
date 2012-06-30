package com.github.jasonrose.crud.om;

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
import javax.ws.rs.core.UriInfo;

/**
 * This interface specifies the contract for a CRUD-based Jersey endpoint.
 * @author Jason Rose
 *
 * @param <E> An entity type.
 */
public interface Resource<E extends AbstractEntity> {

  @Path("")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  Response create(@Context final UriInfo uriInfo, final E entity);

  @Path("{id: \\d+}")
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  Response delete(@Context final UriInfo uriInfo, @PathParam("id") final Long id);

  @Path("{id: \\d+}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response get(@Context final UriInfo uriInfo, @PathParam("id") final Long id);

  @Path("")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response list(@Context final UriInfo uriInfo);

  @Path("{id: \\d+}")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  Response update(@Context final UriInfo uriInfo, @PathParam("id") final Long id, final E entity);
}
