package com.github.jasonrose.crud.scanner;

import java.util.Map;

import com.github.jasonrose.crud.om.Dao;
import com.github.jasonrose.crud.om.DefaultService;
import com.github.jasonrose.crud.security.Authorizer;
import com.github.jasonrose.crud.validation.Validator;
import com.google.common.collect.Maps;

public class GeneratedServiceEmitter extends AbstractEmitter {
  @Override
  public Emission emit(final Model model) {
    final Map<String, Object> context = Maps.newHashMap();
    context.put("package", model.getEntityClassPackageName() + ".generated");
    context.put("entityClassName", model.getEntityClassName());
    context.put("serviceClassName", DefaultService.class.getName());
    context.put("daoClassName", Dao.class.getName());
    context.put("authorizerClassName", Authorizer.class.getName());
    context.put("validatorClassName", Validator.class.getName());
    context.put("entityClassSimpleName", model.getEntityClassSimpleName());
    final String filename = context.get("package") + ".Generated" + context.get("entityClassSimpleName") + "Service";
    return template("GeneratedService.mustache.java", context, filename);
  }
}
