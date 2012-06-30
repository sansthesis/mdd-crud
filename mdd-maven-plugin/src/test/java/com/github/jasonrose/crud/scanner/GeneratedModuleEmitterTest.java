package com.github.jasonrose.crud.scanner;

import org.junit.Assert;
import org.junit.Test;

import com.github.jasonrose.crud.om.Contact;
import com.github.jasonrose.crud.om.Division;
import com.github.jasonrose.crud.om.Person;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;

public class GeneratedModuleEmitterTest {

  @Test
  public void testGenerateModel() throws Exception {
    final Emitter emitter = new GeneratedModuleEmitter();
    final ClassScanner scanner = new ClassScanner(new BeanAnalyzerImpl());
    final Emission out = emitter.emit(ImmutableList.of(scanner.generateModel(Contact.class), scanner.generateModel(Person.class), scanner.generateModel(Division.class)));
    Assert.assertEquals("com.github.jasonrose.crud.om.generated.GeneratedModule", out.getFilename());
    final String verify = CharStreams.toString(Resources.newReaderSupplier(Thread.currentThread().getContextClassLoader().getResource("GeneratedModule.java"), Charsets.UTF_8));
    final String fixedVerify = verify.replaceAll("\\r\\n", "\n");
    final String fixedContent = out.getContent().replaceAll("\\r\\n", "\n");
    Assert.assertEquals(fixedVerify, fixedContent);
  }
}
