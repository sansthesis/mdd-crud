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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.praxissoftware.rest.core.AbstractMapEntity;

public class DefaultResource<E extends AbstractMapEntity, D extends Dao<E>> {
  private final D dao;

  public DefaultResource(final D dao) {
    this.dao = dao;
  }

  @Path("")
  @POST
  @Produces({ "application/json" })
  @Consumes({ "application/json" })
  public Response create(@Context final UriInfo uriInfo, final E entity) {
    return Response.ok(this.dao.create(entity)).build();
  }

  @Path("{id: \\d+}")
  @DELETE
  @Produces({ "application/json" })
  public Response delete(@Context final UriInfo uriInfo, @PathParam("id") final Long id) {
    return Response.ok(Boolean.valueOf(this.dao.delete(id))).build();
  }

  @Path("{id: \\d+}")
  @GET
  @Produces({ "application/json" })
  public Response get(@Context final UriInfo uriInfo, @PathParam("id") final Long id) {
    return Response.ok(this.dao.get(id)).build();
  }

  @Path("")
  @GET
  @Produces({ "application/json" })
  public Response list(@Context final UriInfo uriInfo) {
    return Response.ok(this.dao.list()).build();
  }

  @Path("{id: \\d+}")
  @PUT
  @Produces({ "application/json" })
  @Consumes({ "application/json" })
  public Response update(@Context final UriInfo uriInfo, @PathParam("id") final Long id, final E entity) {
    return Response.ok(this.dao.update(entity)).build();
  }

  protected Dao<E> getDao() {
    return this.dao;
  }
}