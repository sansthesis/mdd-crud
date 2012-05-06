package com.github.jasonrose.crud.scanner;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Set;

import com.github.jasonrose.crud.om.DefaultDao;
import com.github.jasonrose.functional.Functional;
import com.google.common.base.Throwables;
import com.sampullara.mustache.Mustache;
import com.sampullara.mustache.MustacheException;

public class EntityDefaultDaoEmitter extends EntityDaoEmitter {
  
  public EntityDefaultDaoEmitter(final SourceGenerator sourceGenerator, final Functional functional) {
    super(sourceGenerator, functional);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Emission emit(final Model model) {
    Emission output = null;
    try {
      final Mustache mustache = createMustacheTemplate("EntityDefaultDao.mustache.java");

      final Map<String, Object> context = generateContext(model);

      final Set<Map<String, String>> imports = (Set<Map<String, String>>) context.get("imports");
      imports.add(getSourceGenerator().createImport(DefaultDao.class));

      final Writer out = new StringWriter();
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
