package {{{package}}};

@javax.ws.rs.Path("{{{entityClassSimpleName}}}")
public interface Generated{{{entityClassSimpleName}}}Resource {

  @javax.ws.rs.Path("")
  @javax.ws.rs.POST
  @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
  @javax.ws.rs.Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
  javax.ws.rs.core.Response create(@javax.ws.rs.core.Context final javax.ws.rs.core.UriInfo uriInfo, final {{{entityClassName}}} entity);

  @javax.ws.rs.Path("{id: \\d+}")
  @javax.ws.rs.DELETE
  @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
  javax.ws.rs.core.Response delete(@javax.ws.rs.core.Context final javax.ws.rs.core.UriInfo uriInfo, @javax.ws.rs.PathParam("id") final Long id);

  @javax.ws.rs.Path("{id: \\d+}")
  @javax.ws.rs.GET
  @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
  javax.ws.rs.core.Response get(@javax.ws.rs.core.Context final javax.ws.rs.core.UriInfo uriInfo, @javax.ws.rs.PathParam("id") final Long id);

  @javax.ws.rs.Path("")
  @javax.ws.rs.GET
  @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
  javax.ws.rs.core.Response list(@javax.ws.rs.core.Context final javax.ws.rs.core.UriInfo uriInfo);

  @javax.ws.rs.Path("{id: \\d+}")
  @javax.ws.rs.PUT
  @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
  @javax.ws.rs.Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
  javax.ws.rs.core.Response update(@javax.ws.rs.core.Context final javax.ws.rs.core.UriInfo uriInfo, @javax.ws.rs.PathParam("id") final Long id, final {{{entityClassName}}} entity);
}
