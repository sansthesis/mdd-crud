package com.github.jasonrose.crud.scanner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheException;
import com.github.mustachejava.MustacheFactory;
import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.InputSupplier;
import com.google.common.io.Resources;

public abstract class AbstractEmitter implements Emitter {
  @Override
  public Emission emit(final Collection<Model> models) {
    return null;
  }

  @Override
  public Emission emit(final Model model) {
    return null;
  }

  protected Mustache createMustacheTemplate(final String templateName) throws IOException, MustacheException {
    final MustacheFactory factory = new DefaultMustacheFactory();
    final InputSupplier<InputStreamReader> supplier = Resources.newReaderSupplier(getClass().getClassLoader().getResource(templateName), Charsets.UTF_8);
    return factory.compile(supplier.getInput(), templateName);
  }

  protected Emission template(final String templateName, final Object context, final String filename) {
    final Writer out = new StringWriter();
    try {
      final Mustache mustache = createMustacheTemplate(templateName);
      mustache.execute(out, context);
      return new Emission(filename, out.toString());
    } catch( final IOException ioe ) {
      Throwables.propagate(ioe);
    } catch( final MustacheException e ) {
      Throwables.propagate(e);
    }
    throw new IllegalStateException();
  }
}
