package com.github.jasonrose.crud.om;

import java.util.List;

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

import com.github.jasonrose.crud.representation.Representer;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class DefaultResourceTest {
  
  @Mock
  private Service<AbstractEntity> serv;
  
  @Mock
  private Representer<AbstractEntity> representer;
  
  @Mock
  private UriInfo uriInfo;
  
  private AbstractEntity entity;

  private DefaultResource<AbstractEntity, Service<AbstractEntity>, Representer<AbstractEntity>> service;
  
  @Before
  public void setUp() {
    service = new DefaultResource<AbstractEntity, Service<AbstractEntity>, Representer<AbstractEntity>>(serv, representer);
  }
  
  @Test
  public void testCreateInvokesCollaborators() {
    final Response output = service.create(uriInfo, entity);
    BDDMockito.verify(serv).create(entity);
    BDDMockito.verify(representer).generateRepresentation(entity, service.getClass(), "get", uriInfo);
    Assert.assertEquals(Status.CREATED.getStatusCode(), output.getStatus());
  }
  
  @Test
  public void testDeleteInvokesCollaborators() {
    final Response output = service.delete(uriInfo, 1L);
    BDDMockito.verify(serv).delete(1L);
    BDDMockito.verifyZeroInteractions(representer);
    Assert.assertEquals(Status.NO_CONTENT.getStatusCode(), output.getStatus());
  }
  
  @Test
  public void testGetInvokesCollaborators() {
    final Response output = service.get(uriInfo, 1L);
    BDDMockito.verify(serv).get(1L);
    BDDMockito.verify(representer).generateRepresentation(entity, service.getClass(), "get", uriInfo);
    Assert.assertEquals(Status.OK.getStatusCode(), output.getStatus());
  }
  
  @Test
  public void testListInvokesCollaborators() {
    final Response output = service.list(uriInfo);
    final List<AbstractEntity> list = Lists.newArrayList();
    BDDMockito.given(serv.list()).willReturn(list);
    BDDMockito.verify(serv).list();
    BDDMockito.verify(representer).generateListRepresentation(list, service.getClass(), "list", "get", uriInfo);
    Assert.assertEquals(Status.OK.getStatusCode(), output.getStatus());
  }
  
  @Test
  public void testUpdateInvokesCollaborators() {
    final Response output = service.update(uriInfo, 1L, entity);
    BDDMockito.verify(serv).update(1L, entity);
    BDDMockito.verify(representer).generateRepresentation(entity, service.getClass(), "get", uriInfo);
    Assert.assertEquals(Status.OK.getStatusCode(), output.getStatus());
  }
}
