package com.github.jasonrose.crud.scanner;

import java.util.Map;

import com.github.jasonrose.crud.om.DefaultResource;
import com.github.jasonrose.crud.om.Service;
import com.google.common.collect.Maps;

public class GeneratedResourceEmitter extends AbstractEmitter {
  @Override
  public Emission emit(final Model model) {
    final Map<String, Object> context = Maps.newHashMap();
    final String packageName = model.getEntityClassPackageName() + ".generated";
    context.put("package", packageName);
    context.put("entityClassName", model.getEntityClassName());
    context.put("entityClassSimpleName", model.getEntityClassSimpleName());
    context.put("entityClassPackageName", model.getEntityClassPackageName());
    context.put("resourceClassName", DefaultResource.class.getName());
    context.put("serviceClassName", Service.class.getName());
    final String filename = packageName + ".Generated" + model.getEntityClassSimpleName() + "Resource";
    return template("GeneratedResource.mustache.java", context, filename);
  }
}
