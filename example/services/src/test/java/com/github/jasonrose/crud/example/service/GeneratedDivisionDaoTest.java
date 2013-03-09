package com.github.jasonrose.crud.example.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.jasonrose.crud.om.Division;
import com.github.jasonrose.crud.om.generated.GeneratedDivisionDao;

public class GeneratedDivisionDaoTest {

  private EntityManagerFactory factory;
  private GeneratedDivisionDao service;

  @Before
  public void setUp() throws Exception {
    factory = Persistence.createEntityManagerFactory("mdd-crud-example");
    final EntityManager em = factory.createEntityManager();
    service = new GeneratedDivisionDao(em);
    int i = 0;
    for( final String name : "a a a b c".split(" ") ) {
      em.getTransaction().begin();
      final Division division = new Division();
      division.setName(name);
      division.setNumber(name + i++);
      service.create(division);
      em.getTransaction().commit();
    }
  }

  @After
  public void tearDown() throws Exception {
    factory.close();
  }

  @Test
  public void testOnePropList() {
    final List<Division> list = service.finder().name("a").list();
    Assert.assertEquals(3, list.size());
  }

  @Test
  public void testOnePropListMiss() {
    final List<Division> list = service.finder().name("qqqq").list();
    Assert.assertEquals(0, list.size());
  }

  @Test(expected = NonUniqueResultException.class)
  public void testOnePropGetDies() {
    service.finder().name("a").get();
  }

  public void testOnePropGet() {
    final Division d = service.finder().name("b").get();
    Assert.assertEquals("b", d.getName());
    Assert.assertEquals("b3", d.getNumber());
  }

  public void testOnePropGetMiss() {
    final Division d = service.finder().name("qqqqq").get();
    Assert.assertNull(d);
  }

}
