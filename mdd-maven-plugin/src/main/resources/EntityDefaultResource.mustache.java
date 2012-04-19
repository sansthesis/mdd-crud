package {{{package}}};

{{#imports}}
import {{{import}}};
{{/imports}}

@Path("{{{entityClassName}}}")
public class {{{entityClassName}}}DefaultResource extends DefaultResource<{{{entityClassName}}}, {{{entityClassName}}}Dao> {

  public {{{entityClassName}}}DefaultResource(final {{{entityClassName}}}Dao dao) {
    super(dao);
  }

  @Path("{id: \\d+}")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Override
  public Response update(@Context final UriInfo uriInfo, @PathParam("id") final Long id, final {{{entityClassName}}} entity) {
    return super.update(uriInfo, id, entity);
  }

  @Path("")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Override
  public Response create(@Context final UriInfo uriInfo, final {{{entityClassName}}} entity) {
    return super.create(uriInfo, entity);
  }
}
