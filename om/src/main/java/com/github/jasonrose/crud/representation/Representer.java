package com.github.jasonrose.crud.representation;

import javax.ws.rs.core.UriInfo;

import com.github.jasonrose.crud.om.AbstractEntity;
import com.praxissoftware.rest.core.Link;
import com.praxissoftware.rest.core.Representation;

/**
 * A Representer is in charge of generating representations of entities.
 * @author Jason Rose
 *
 * @param <T> Any entity type.
 */
public interface Representer<T extends AbstractEntity> {

  Representation generateRepresentation(T entity, Class<?> resourceClass, String getMethodName, UriInfo uriInfo);
  
  Representation generateBriefRepresentation(T entity, Class<?> resourceClass, String getMethodName, UriInfo uriInfo);

  Link generateLink(T entity, Class<?> resourceClass, String methodName, UriInfo uriInfo, String relation);
}
