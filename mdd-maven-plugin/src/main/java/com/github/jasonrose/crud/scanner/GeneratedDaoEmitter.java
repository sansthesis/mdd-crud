package com.github.jasonrose.crud.scanner;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import com.github.jasonrose.crud.om.DefaultDao;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.sampullara.mustache.Mustache;
import com.sampullara.mustache.MustacheException;

public class GeneratedDaoEmitter extends AbstractEmitter {
  @Override
  public Emission emit(final Model model) {
    Emission output = null;
    final Map<String, Object> context = Maps.newHashMap();
    context.put("package", model.getEntityClassPackageName() + ".generated");
    context.put("entityClassName", model.getEntityClassName());
    context.put("daoClassName", DefaultDao.class.getName());
    context.put("entityClassSimpleName", model.getEntityClassSimpleName());
    try {
      final Mustache mustache = createMustacheTemplate("GeneratedDao.mustache.java");
      final Writer out = new StringWriter();
      mustache.execute(out, context);
      output = new Emission(context.get("package") + ".Generated" + context.get("entityClassSimpleName") + "Dao", out.toString());
    } catch( IOException ioe ) {
      Throwables.propagate(ioe);
    } catch( MustacheException me ) {
      Throwables.propagate(me);
    }
    return output;
  }
}
