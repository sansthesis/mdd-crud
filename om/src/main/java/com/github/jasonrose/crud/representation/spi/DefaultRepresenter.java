package com.github.jasonrose.crud.representation.spi;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

import com.github.jasonrose.crud.om.AbstractEntity;
import com.github.jasonrose.crud.representation.Representer;
import com.praxissoftware.rest.core.BasicRepresentation;
import com.praxissoftware.rest.core.Link;
import com.praxissoftware.rest.core.Representation;

public class DefaultRepresenter<T extends AbstractEntity> implements Representer<T> {
  
  @Override
  public Link generateLink(T entity, Class<?> resourceClass, String methodName, UriInfo uriInfo, String relation) {
    return new Link.Builder().rel(relation).type("application/json").href(uriInfo.getBaseUriBuilder().path(resourceClass).path(resourceClass, methodName).build(entity.getId())).build();
  }

  @Override
  public Representation generateBriefRepresentation(T entity, Class<?> resourceClass, String getMethodName, UriInfo uriInfo) {
    final Representation representation = new BasicRepresentation();
    representation.getLinks().add(generateLink(entity, resourceClass, getMethodName, uriInfo, "self"));
    return representation;
  }

  @Override
  public Representation generateRepresentation(T entity, Class<?> resourceClass, String getMethodName, UriInfo uriInfo) {
    final BasicRepresentation representation = new BasicRepresentation();
    representation.getLinks().add(generateLink(entity, resourceClass, getMethodName, uriInfo, "self"));
    representation.putAll(entity);
    return representation;
  }
}
