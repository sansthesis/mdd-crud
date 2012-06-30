package com.github.jasonrose.crud.scanner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.common.io.Resources;
import com.sampullara.mustache.Mustache;
import com.sampullara.mustache.MustacheBuilder;
import com.sampullara.mustache.MustacheException;

public abstract class AbstractEmitter implements Emitter {
  @Override
  public Emission emit(final Model model) {
    return null;
  }

  @Override
  public Emission emit(final Collection<Model> models) {
    return null;
  }
  
  protected Mustache createMustacheTemplate(final String templateName) throws IOException, MustacheException {
    final InputSupplier<InputStreamReader> supplier = Resources.newReaderSupplier(getClass().getClassLoader().getResource(templateName), Charsets.UTF_8);
    final String template = CharStreams.toString(supplier);
    return new MustacheBuilder().parse(template, templateName);
  }
  
  protected Emission template(String templateName, Object context, String filename) {
    final Writer out = new StringWriter();
    try {
      final Mustache mustache = createMustacheTemplate(templateName);
      mustache.execute(out, context);
      return new Emission(filename, out.toString());
    } catch( IOException ioe ) {
      Throwables.propagate(ioe);
    } catch( MustacheException e ) {
      Throwables.propagate(e);
    }
    throw new IllegalStateException();
  }
}
