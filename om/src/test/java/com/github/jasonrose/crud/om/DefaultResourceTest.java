package com.github.jasonrose.crud.om;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultResourceTest {
  
  @Mock
  private Service<AbstractEntity> serv;
  
  @Mock
  private UriInfo uriInfo;
  
  private AbstractEntity entity;

  private DefaultResource<AbstractEntity, Service<AbstractEntity>> service;
  
  @Before
  public void setUp() {
    service = new DefaultResource<AbstractEntity, Service<AbstractEntity>>(serv);
  }
  
  @Test
  public void testCreateInvokesCollaborators() {
    final Response output = service.create(uriInfo, entity);
    BDDMockito.verify(serv).create(entity);
    Assert.assertEquals(Status.CREATED.getStatusCode(), output.getStatus());
  }
  
  @Test
  public void testDeleteInvokesCollaborators() {
    final Response output = service.delete(uriInfo, 1L);
    BDDMockito.verify(serv).delete(1L);
    Assert.assertEquals(Status.NO_CONTENT.getStatusCode(), output.getStatus());
  }
  
  @Test
  public void testGetInvokesCollaborators() {
    final Response output = service.get(uriInfo, 1L);
    BDDMockito.verify(serv).get(1L);
    Assert.assertEquals(Status.OK.getStatusCode(), output.getStatus());
  }
  
  @Test
  public void testListInvokesCollaborators() {
    final Response output = service.list(uriInfo);
    BDDMockito.verify(serv).list();
    Assert.assertEquals(Status.OK.getStatusCode(), output.getStatus());
  }
  
  @Test
  public void testUpdateInvokesCollaborators() {
    final Response output = service.update(uriInfo, 1L, entity);
    BDDMockito.verify(serv).update(1L, entity);
    Assert.assertEquals(Status.OK.getStatusCode(), output.getStatus());
  }
}
