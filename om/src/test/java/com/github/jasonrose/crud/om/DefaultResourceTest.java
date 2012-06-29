package com.github.jasonrose.crud.om;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.jasonrose.crud.security.Authorizer;
import com.github.jasonrose.crud.security.Authorizer.Operation;
import com.github.jasonrose.crud.validation.Validator;

@RunWith(MockitoJUnitRunner.class)
public class DefaultResourceTest {
  
  @Mock
  private Dao<AbstractEntity> dao;
  
  @Mock
  private Authorizer<AbstractEntity> authorizer;
  
  @Mock
  private Validator<AbstractEntity> validator;
  
  @Mock
  private UriInfo uriInfo;
  
  private AbstractEntity entity;

  private DefaultResource<AbstractEntity, Dao<AbstractEntity>, Authorizer<AbstractEntity>, Validator<AbstractEntity>> service;
  
  @Before
  public void setUp() {
    service = new DefaultResource<AbstractEntity, Dao<AbstractEntity>, Authorizer<AbstractEntity>, Validator<AbstractEntity>>(dao, authorizer, validator);
  }
  
  @Test
  public void testCreateInvokesCollaborators() {
    final Response output = service.create(uriInfo, entity);
    BDDMockito.verify(authorizer).authorize(Operation.CREATE, entity);
    BDDMockito.verify(validator).validate(entity);
    BDDMockito.verify(dao).create(entity);
    Assert.assertEquals(Status.CREATED.getStatusCode(), output.getStatus());
  }
  
  @Test
  public void testDeleteInvokesCollaborators() {
    final Response output = service.delete(uriInfo, 1L);
    BDDMockito.verify(authorizer).authorize(Operation.DELETE, 1L);
    BDDMockito.verify(dao).delete(1L);
    Assert.assertEquals(Status.NO_CONTENT.getStatusCode(), output.getStatus());
    BDDMockito.verifyNoMoreInteractions(validator);
  }
  
  @Test
  public void testGetInvokesCollaborators() {
    final Response output = service.get(uriInfo, 1L);
    BDDMockito.verify(authorizer).authorize(Operation.READ, 1L);
    BDDMockito.verify(dao).get(1L);
    Assert.assertEquals(Status.OK.getStatusCode(), output.getStatus());
    BDDMockito.verifyNoMoreInteractions(validator);
  }
  
  @Test
  public void testListInvokesCollaborators() {
    final Response output = service.list(uriInfo);
    BDDMockito.verify(authorizer).authorize(Operation.READ);
    BDDMockito.verify(dao).list();
    Assert.assertEquals(Status.OK.getStatusCode(), output.getStatus());
    BDDMockito.verifyNoMoreInteractions(validator);
  }
  
  @Test
  public void testUpdateInvokesCollaborators() {
    final Response output = service.update(uriInfo, 1L, entity);
    BDDMockito.verify(authorizer).authorize(Operation.UPDATE, entity);
    BDDMockito.verify(dao).update(entity);
    BDDMockito.verify(validator).validate(entity);
    Assert.assertEquals(Status.OK.getStatusCode(), output.getStatus());
  }
}
