package com.github.jasonrose.crud.representation.spi;

import java.util.Collection;

import javax.ws.rs.core.UriInfo;

import com.github.jasonrose.crud.om.AbstractEntity;
import com.github.jasonrose.crud.representation.Representer;
import com.praxissoftware.rest.core.BasicRepresentation;
import com.praxissoftware.rest.core.Link;
import com.praxissoftware.rest.core.Representation;

public class DefaultRepresenter<T extends AbstractEntity> implements Representer<T> {

  @Override
  public Link generateLink(final T entity, final Class<?> resourceClass, final String methodName, final UriInfo uriInfo, final String relation) {
    return new Link.Builder().rel(relation).type("application/json").href(uriInfo.getBaseUriBuilder().path(resourceClass).path(resourceClass, methodName).build(entity.getId())).build();
  }

  @Override
  public Representation generateListRepresentation(final Collection<T> entities, final Class<?> resourceClass, final String listMethodName, final String getMethodName, final UriInfo uriInfo) {
    final BasicRepresentation representation = new BasicRepresentation();
    representation.getLinks().add(new Link.Builder().rel("self").type("application/json").href(uriInfo.getBaseUriBuilder().path(resourceClass).path(resourceClass, listMethodName).build()).build());
    for( final T entity : entities ) {
      representation.getLinks().add(generateLink(entity, resourceClass, getMethodName, uriInfo, "item"));
    }
    return representation;
  }

  @Override
  public Representation generateRepresentation(final T entity, final Class<?> resourceClass, final String getMethodName, final UriInfo uriInfo) {
    final BasicRepresentation representation = new BasicRepresentation();
    representation.getLinks().add(generateLink(entity, resourceClass, getMethodName, uriInfo, "self"));
    representation.putAll(entity);
    return representation;
  }
}
