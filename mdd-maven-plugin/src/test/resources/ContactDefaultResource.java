package com.github.jasonrose.crud.om.generated;

import com.github.jasonrose.crud.om.DefaultResource;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import com.github.jasonrose.crud.om.Contact;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import com.github.jasonrose.crud.om.generated.ContactDao;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("Contact")
public class ContactDefaultResource extends DefaultResource<Contact, ContactDao> {

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
