package com.github.jasonrose.crud.scanner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Set;

import com.github.jasonrose.crud.om.DefaultDao;
import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.common.io.Resources;
import com.sampullara.mustache.Mustache;
import com.sampullara.mustache.MustacheBuilder;
import com.sampullara.mustache.MustacheException;

public class EntityDefaultDaoEmitter extends EntityDaoEmitter {

  @SuppressWarnings("unchecked")
  @Override
  public Emission emit(final Model model) {
    Writer out = null;
    Emission output = null;
    try {
      final InputSupplier<InputStreamReader> supplier = Resources.newReaderSupplier(getClass().getClassLoader().getResource("EntityDefaultDao.mustache.java"), Charsets.UTF_8);
      final String template = CharStreams.toString(supplier);

      final Mustache mustache = new MustacheBuilder().parse(template, "EntityDefaultDao.mustache.java");

      out = new StringWriter();

      final Map<String, Object> context = generateContext(model);

      final Set<Map<String, Object>> imports = (Set<Map<String, Object>>) context.get("imports");
      imports.add(createImport(DefaultDao.class.getName()));

      mustache.execute(out, context);
      output = new Emission(context.get("package") + "." + context.get("entityClassName") + "DefaultDao", out.toString());
    } catch( IOException ioe ) {
      Throwables.propagate(ioe);
    } catch( MustacheException me ) {
      Throwables.propagate(me);
    }
    return output;
  }

}