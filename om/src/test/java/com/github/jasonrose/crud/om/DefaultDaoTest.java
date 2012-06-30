package com.github.jasonrose.crud.om;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDaoTest {

  @Mock
  private EntityManager entityManager;
  
  private DefaultDao<AbstractEntity> service;
  
  private AbstractEntity entity;
  
  @Before
  public void setUp() {
    service = new DefaultDao<AbstractEntity>(AbstractEntity.class, entityManager);
  }
  
  @Test
  public void testCreateInvokesCollaborators() {
    Object output = service.create(entity);
    BDDMockito.verify(entityManager).persist(entity);
    Assert.assertSame(entity, output);
  }
  
  @Test
  public void testDeleteInvokesCollaborators() {
    BDDMockito.given(entityManager.find(AbstractEntity.class, 1L)).willReturn(entity);
    service.delete(1L);
    BDDMockito.verify(entityManager).remove(entity);
  }
  
  @Test
  public void testGetInvokesCollaborators() {
    BDDMockito.given(entityManager.find(AbstractEntity.class, 1L)).willReturn(entity);
    final Object output = service.get(1L);
    BDDMockito.verify(entityManager).find(AbstractEntity.class, 1L);
    Assert.assertSame(entity, output);
  }
  
  @Test
  public void testListInvokesCollaborators() {
    final Query query = BDDMockito.mock(Query.class);
    final List<AbstractEntity> list = Arrays.asList(entity);
    BDDMockito.given(entityManager.createQuery(Matchers.anyString())).willReturn(query);
    BDDMockito.given(query.getResultList()).willReturn(list);
    final Object output = service.list();
    BDDMockito.verify(entityManager).createQuery(Matchers.anyString());
    BDDMockito.verify(query).getResultList();
    Assert.assertSame(list, output);
  }
  
  @Test
  public void testUpdateInvokesCollaborators() {
    Object output = service.update(entity);
    BDDMockito.verify(entityManager).persist(entity);
    Assert.assertSame(entity, output);
  }
}
