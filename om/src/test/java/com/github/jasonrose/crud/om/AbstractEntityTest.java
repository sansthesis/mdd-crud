package com.github.jasonrose.crud.om;

import junit.framework.Assert;

import org.junit.Test;

public class AbstractEntityTest {

  private class Impl extends AbstractEntity {
  }

  @Test
  public void testEquals() throws Exception {
    final Impl a1 = new Impl();
    final Impl a2 = new Impl();
    Assert.assertEquals(a1, a2);
    Assert.assertFalse(a1.equals(null));
    a1.setId(1L);
    a2.setId(a1.getId() + 1);
    Assert.assertFalse(a1.equals(a2));
    a1.setId(2L);
    a2.setId(a1.getId());
    Assert.assertEquals(a1, a2);
  }

  @Test
  public void testHashCode() throws Exception {
    final Impl a1 = new Impl();
    final Impl a2 = new Impl();
    Assert.assertEquals(a1.hashCode(), a2.hashCode());
    a1.setId(1L);
    a2.setId(a1.getId() + 1);
    Assert.assertFalse(a1.hashCode() == a2.hashCode());
    a1.setId(2L);
    a2.setId(a1.getId());
    Assert.assertEquals(a1.hashCode(), a2.hashCode());
  }

  @Test
  public void testToString() throws Exception {
    final Impl e = new Impl();
    e.setId(4L);
    final String s = e.toString();
    Assert.assertEquals("{id=4}", s);
  }
}
