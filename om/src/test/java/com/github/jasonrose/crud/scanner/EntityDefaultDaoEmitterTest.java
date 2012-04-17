package com.github.jasonrose.crud.scanner;

import org.junit.Assert;
import org.junit.Test;

import com.github.jasonrose.crud.om.Contact;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;

public class EntityDefaultDaoEmitterTest {

  @Test
  public void testGenerateModel() throws Exception {
    final EntityDefaultDaoEmitter emitter = new EntityDefaultDaoEmitter();
    final ClassScanner scanner = new ClassScanner();
    final Model model = scanner.generateModel(Contact.class);
    final Emission out = emitter.emit(model);
    Assert.assertEquals("com.github.jasonrose.crud.om.generated.ContactDefaultDao", out.getFilename());
    final String verify = CharStreams.toString(Resources.newReaderSupplier(Thread.currentThread().getContextClassLoader().getResource("ContactDefaultDao.java"), Charsets.UTF_8));
    final String fixedVerify = verify.replaceAll("\\r\\n", "\n");
    final String fixedContent = out.getContent().replaceAll("\\r\\n", "\n");
    Assert.assertEquals(fixedVerify, fixedContent);
  }
}
