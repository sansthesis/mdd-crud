package com.github.jasonrose.crud.om.generated;

import com.github.jasonrose.crud.om.DefaultResource;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import com.github.jasonrose.crud.om.Contact;
import com.github.jasonrose.crud.om.generated.ContactDao;

@Path("Contact")
public class ContactDefaultResource extends DefaultResource<Contact, ContactDao> {

  @Inject
  public ContactDefaultResource(final ContactDao dao) {
    super(dao);
  }

  @Path("{id: \\d+}")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Override
  public Response update(@Context final UriInfo uriInfo, @PathParam("id") final Long id, final Contact entity) {
    return super.update(uriInfo, id, entity);
  }

  @Path("")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Override
  public Response create(@Context final UriInfo uriInfo, final Contact entity) {
    return super.create(uriInfo, entity);
  }
}
