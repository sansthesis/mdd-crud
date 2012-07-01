package com.github.jasonrose.crud.representation.spi;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.jasonrose.crud.om.AbstractEntity;
import com.praxissoftware.rest.core.BasicRepresentation;
import com.praxissoftware.rest.core.Link;
import com.praxissoftware.rest.core.Representation;
import com.sun.jersey.api.uri.UriBuilderImpl;

@RunWith(MockitoJUnitRunner.class)
public class DefaultRepresenterTest {

  private DefaultRepresenter<AbstractEntity> service;
  private AbstractEntity entity;
  
  @Mock
  private UriInfo uriInfo;
  
  @Before
  public void setUp() {
    service = new DefaultRepresenter<AbstractEntity>();
    entity = new AbstractEntity() {};
    entity.setId(100L);
    
    BDDMockito.given(uriInfo.getAbsolutePathBuilder()).willReturn(new UriBuilderImpl(), new UriBuilderImpl(), new UriBuilderImpl(), new UriBuilderImpl());
    BDDMockito.given(uriInfo.getBaseUriBuilder()).willReturn(new UriBuilderImpl(), new UriBuilderImpl(), new UriBuilderImpl(), new UriBuilderImpl());
    BDDMockito.given(uriInfo.getRequestUriBuilder()).willReturn(new UriBuilderImpl(), new UriBuilderImpl(), new UriBuilderImpl(), new UriBuilderImpl());
  }
  
  @Test
  public void testGenerateRepresentationHasSelfLink() {
    final Representation output = service.generateRepresentation(entity, TestResource.class, "get", uriInfo);
    final List<Link> links = output.getLinks();
    Assert.assertEquals(1, links.size());
    Link selfLink = links.get(0);
    Assert.assertEquals("test/100", selfLink.getHref().toString());
    Assert.assertEquals("self", selfLink.getRel());
    Assert.assertEquals("application/json", selfLink.getType());
  }
  
  @Test
  public void testGenerateRepresentationHasProperties() {
    entity.put("test", "ponies");
    final BasicRepresentation output = (BasicRepresentation) service.generateRepresentation(entity, TestResource.class, "get", uriInfo);
    Assert.assertEquals("ponies", output.get("test"));
    Assert.assertEquals(100L, output.get("id"));
  }
  
  @Test
  public void testGenerateBriefRepresentationHasSelfLink() {
    final Representation output = service.generateBriefRepresentation(entity, TestResource.class, "get", uriInfo);
    final List<Link> links = output.getLinks();
    Assert.assertEquals(1, links.size());
    Link selfLink = links.get(0);
    Assert.assertEquals("test/100", selfLink.getHref().toString());
    Assert.assertEquals("self", selfLink.getRel());
    Assert.assertEquals("application/json", selfLink.getType());
  }
  
  @Test
  public void testGenerateBriefRepresentationHasNoOtherProperties() {
    final BasicRepresentation output = (BasicRepresentation) service.generateBriefRepresentation(entity, TestResource.class, "get", uriInfo);
    Assert.assertEquals(1, output.keySet().size());
    Assert.assertEquals("links", output.keySet().iterator().next());
  }
  
  @Path("test")
  private static class TestResource {
    
    @GET
    @Path("{id}")
    public Response get(@Context UriInfo uriInfo, @PathParam("id") Long id) {
      return null;
    }
    
    @GET
    @Path("")
    public Response list(@Context UriInfo uriInfo) {
      return null;
    }
  }
}
