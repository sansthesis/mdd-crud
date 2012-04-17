package com.github.jasonrose.crud.scanner;

import org.junit.Assert;
import org.junit.Test;

import com.github.jasonrose.crud.om.Contact;

public class ClassScannerTest {

  @Test
  public void testGenerateModel() throws Exception {
    final ClassScanner scanner = new ClassScanner();
    final Model model = scanner.generateModel(Contact.class);
    Assert.assertEquals(Contact.class, model.getEntityClass());
    Assert.assertEquals(Contact.class.getSimpleName(), model.getEntityClassSimpleName());
    Assert.assertEquals(Contact.class.getName(), model.getEntityClassName());
    Assert.assertEquals(Contact.class.getPackage().getName(), model.getEntityClassPackageName());
    Assert.assertEquals(5, model.getProperties().size());
    Assert.assertEquals(1, model.getRelationships().size());
  }
}
