package com.github.jasonrose.crud.om;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
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
public class DefaultServiceTest {
  
  @Mock
  private Dao<AbstractEntity> dao;
  
  @Mock
  private Authorizer<AbstractEntity> authorizer;
  
  @Mock
  private Validator<AbstractEntity> validator;
  
  private AbstractEntity entity;

  private DefaultService<AbstractEntity, Dao<AbstractEntity>, Authorizer<AbstractEntity>, Validator<AbstractEntity>> service;
  
  @Before
  public void setUp() {
    service = new DefaultService<AbstractEntity, Dao<AbstractEntity>, Authorizer<AbstractEntity>, Validator<AbstractEntity>>(dao, authorizer, validator);
  }
  
  @Test
  public void testCreateInvokesCollaborators() {
    BDDMockito.given(dao.create(entity)).willReturn(entity);
    final Object output = service.create(entity);
    BDDMockito.verify(authorizer).authorize(Operation.CREATE, entity);
    BDDMockito.verify(validator).validate(entity);
    BDDMockito.verify(dao).create(entity);
    Assert.assertSame(entity, output);
  }
  
  @Test
  public void testDeleteInvokesCollaborators() {
    service.delete(1L);
    BDDMockito.verify(authorizer).authorize(Operation.DELETE, 1L);
    BDDMockito.verify(dao).delete(1L);
    BDDMockito.verifyNoMoreInteractions(validator);
  }
  
  @Test
  public void testGetInvokesCollaborators() {
    BDDMockito.given(dao.get(1L)).willReturn(entity);
    final Object output = service.get(1L);
    BDDMockito.verify(authorizer).authorize(Operation.READ, 1L);
    BDDMockito.verify(dao).get(1L);
    BDDMockito.verifyNoMoreInteractions(validator);
    Assert.assertSame(entity, output);
  }
  
  @Test
  public void testListInvokesCollaborators() {
    final List<AbstractEntity> list = Arrays.asList(entity);
    BDDMockito.given(dao.list()).willReturn(list);
    final List<?> output = service.list();
    BDDMockito.verify(authorizer).authorize(Operation.READ);
    BDDMockito.verify(dao).list();
    BDDMockito.verifyNoMoreInteractions(validator);
    Assert.assertSame(list, output);
  }
  
  @Test
  public void testUpdateInvokesCollaborators() {
    BDDMockito.given(dao.update(entity)).willReturn(entity);
    final Object output = service.update(1L, entity);
    BDDMockito.verify(authorizer).authorize(Operation.UPDATE, entity);
    BDDMockito.verify(dao).update(entity);
    BDDMockito.verify(validator).validate(entity);
    Assert.assertSame(entity, output);
  }
}
