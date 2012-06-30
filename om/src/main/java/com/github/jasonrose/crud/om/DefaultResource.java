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

import com.github.jasonrose.crud.security.Authorizer;
import com.github.jasonrose.crud.security.Authorizer.Operation;
import com.github.jasonrose.crud.validation.Validator;
import com.praxissoftware.rest.core.AbstractMapEntity;

public class DefaultResource<E extends AbstractMapEntity, D extends Dao<E>, A extends Authorizer<E>, V extends Validator<E>> {

  protected final D dao;
  protected final A authorizer;
  protected final V validator;

  @Inject
  public DefaultResource(final D dao, final A authorizer, final V validator) {
    this.dao = dao;
    this.authorizer = authorizer;
    this.validator = validator;
  }

  @Path("")
  @POST
  @Produces({ MediaType.APPLICATION_JSON })
  @Consumes({ MediaType.APPLICATION_JSON })
  public Response create(@Context final UriInfo uriInfo, final E entity) {
    authorizer.authorize(Operation.CREATE, entity);
    validator.validate(entity);
    return Response.ok(dao.create(entity)).status(Status.CREATED).build();
  }

  @Path("{id: \\d+}")
  @DELETE
  @Produces({ MediaType.APPLICATION_JSON })
  public Response delete(@Context final UriInfo uriInfo, @PathParam("id") final Long id) {
    authorizer.authorize(Operation.DELETE, id);
    dao.delete(id);
    return Response.status(Status.NO_CONTENT).build();
  }

  @Path("{id: \\d+}")
  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response get(@Context final UriInfo uriInfo, @PathParam("id") final Long id) {
    authorizer.authorize(Operation.READ, id);
    return Response.ok(dao.get(id)).build();
  }

  @Path("")
  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response list(@Context final UriInfo uriInfo) {
    authorizer.authorize(Operation.READ);
    return Response.ok(dao.list()).build();
  }

  @Path("{id: \\d+}")
  @PUT
  @Produces({ MediaType.APPLICATION_JSON })
  @Consumes({ MediaType.APPLICATION_JSON })
  public Response update(@Context final UriInfo uriInfo, @PathParam("id") final Long id, final E entity) {
    authorizer.authorize(Operation.UPDATE, entity);
    validator.validate(entity);
    return Response.ok(dao.update(entity)).build();
  }
}
