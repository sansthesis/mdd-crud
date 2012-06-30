package com.github.jasonrose.crud.scanner;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import com.github.jasonrose.crud.om.DefaultResource;
import com.github.jasonrose.crud.om.Service;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.sampullara.mustache.Mustache;
import com.sampullara.mustache.MustacheException;

public class GeneratedResourceEmitter extends AbstractEmitter {
  @Override
  public Emission emit(final Model model) {
    Writer out = null;
    Emission output = null;
    final Map<String, Object> context = Maps.newHashMap();
    final String packageName = model.getEntityClassPackageName() + ".generated";
    context.put("package", packageName);
    context.put("entityClassName", model.getEntityClassName());
    context.put("entityClassSimpleName", model.getEntityClassSimpleName());
    context.put("entityClassPackageName", model.getEntityClassPackageName());
    context.put("resourceClassName", DefaultResource.class.getName());
    context.put("serviceClassName", Service.class.getName());
    
    try {
      final Mustache mustache = createMustacheTemplate("GeneratedResource.mustache.java"); 

      out = new StringWriter();

      mustache.execute(out, context);
      output = new Emission(packageName + ".Generated" + model.getEntityClassSimpleName() + "Resource", out.toString());
    } catch( IOException ioe ) {
      Throwables.propagate(ioe);
    } catch( MustacheException me ) {
      Throwables.propagate(me);
    }
    return output;
  }
}
