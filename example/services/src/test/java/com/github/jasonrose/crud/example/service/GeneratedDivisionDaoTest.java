package com.github.jasonrose.crud.example.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;

import com.github.jasonrose.crud.om.Contact;
import com.github.jasonrose.crud.om.Division;
import com.github.jasonrose.crud.om.Preds;
import com.github.jasonrose.crud.om.generated.GeneratedDivisionDao;
import com.google.common.collect.Lists;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GeneratedDivisionDaoTest {

  private EntityManagerFactory factory;
  private GeneratedDivisionDao service;

  @Before
  public void setUp() throws Exception {
    factory = Persistence.createEntityManagerFactory("mdd-crud-example");
    final EntityManager em = factory.createEntityManager();
    service = new GeneratedDivisionDao(em);
    int i = 0;
    final List<Contact> contacts = Lists.newArrayList();
    em.getTransaction().begin();
    for( final String name : "a a a b c".split(" ") ) {
      final Contact contact = new Contact();
      contact.setFirstName(name);
      contact.setEmail(name + "@com.com");
      contact.setLastName(name);
      contact.setZipCode("00000");
      contacts.add(contact);
      
      final Division division = new Division();
      division.setName(name);
      division.setDateProperty(new Date(System.currentTimeMillis() - (1000l * 60 * 60 * 24 * 365 * i)));
      division.setNumber(name + i++);
      division.getContacts().addAll(contacts);
      em.persist(contact);
      service.create(division);
    }
    em.getTransaction().commit();
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

  @Test
  public void testOnePropGet() {
    final Division d = service.finder().name("b").get();
    Assert.assertEquals("b", d.getName());
    Assert.assertEquals("b3", d.getNumber());
  }

  @Test(expected = NoResultException.class)
  public void testOnePropGetMiss() {
    service.finder().name("qqqqq").get();
  }

  @Test
  public void testIsNull() {
    Assert.assertEquals(5, service.finder().nullableProperty(Preds.<String> eq(null)).list().size());
    Assert.assertEquals(5, service.finder().nullableProperty(Preds.<String> isNull()).list().size());
  }

  @Test
  public void testIn() {
    Assert.assertEquals(2, service.finder().name(Preds.in(Arrays.asList("b", "c"))).list().size());
    Assert.assertEquals(3, service.finder().name(Preds.in("a")).list().size());
    Assert.assertEquals(0, service.finder().name(Preds.in("aoeuaoe")).list().size());
  }

  @Test
  public void testNot() {
    Assert.assertEquals(3, service.finder().name(Preds.not(Preds.in(Arrays.asList("b", "c")))).list().size());
    Assert.assertEquals(2, service.finder().name(Preds.not(Preds.in("a"))).list().size());
    Assert.assertEquals(5, service.finder().name(Preds.not(Preds.in("aoeuaoe"))).list().size());
  }

  @Test
  public void testAnd() {
    Assert.assertEquals(3, service.finder().name(Preds.and(Preds.eq("a"))).list().size());
    Assert.assertEquals(0, service.finder().name(Preds.and(Preds.eq("a"), Preds.eq("b"))).list().size());
  }

  @Test
  public void testOr() {
    Assert.assertEquals(3, service.finder().name(Preds.or(Preds.eq("a"))).list().size());
    Assert.assertEquals(4, service.finder().name(Preds.or(Preds.eq("a"), Preds.eq("b"))).list().size());
  }

  @Test
  public void testLt() {
    Assert.assertEquals(3, service.finder().id(Preds.lt(4l)).list().size());
    Assert.assertEquals(2, service.finder().dateProperty(Preds.lt(new Date(System.currentTimeMillis() - (1000l * 60 * 60 * 24 * 365 * 3)))).list().size());
    Assert.assertEquals(3, service.finder().name(Preds.lt("b")).list().size());
  }

  @Test
  public void testLte() {
    Assert.assertEquals(4, service.finder().id(Preds.lte(4l)).list().size());
    Assert.assertEquals(3, service.finder().name(Preds.lte("a")).list().size());
  }

  @Test
  public void testGt() {
    Assert.assertEquals(1, service.finder().id(Preds.gt(4l)).list().size());
    Assert.assertEquals(3, service.finder().dateProperty(Preds.gt(new Date(System.currentTimeMillis() - (1000l * 60 * 60 * 24 * 365 * 3)))).list().size());
    Assert.assertEquals(1, service.finder().name(Preds.gt("b")).list().size());
  }

  @Test
  public void testGte() {
    Assert.assertEquals(2, service.finder().id(Preds.gte(4l)).list().size());
    Assert.assertEquals(5, service.finder().name(Preds.gte("a")).list().size());
  }
  
  @Test
  public void testEntity() {
    Assert.assertEquals(5, service.finder().contacts(1l).list().size());
    Assert.assertEquals(4, service.finder().contacts(2l).list().size());
    Assert.assertEquals(1, service.finder().contacts(5l).list().size());
    Assert.assertEquals(3, service.finder().contacts(Preds.or(Preds.eq(3l), Preds.eq(4l))).list().size());
    Assert.assertEquals("c", service.finder().contacts(5l).get().getName());
  }

}
