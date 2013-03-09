package com.github.jasonrose.crud.scanner;

import java.util.Map;

import com.github.jasonrose.crud.om.AbstractFinder;
import com.github.jasonrose.crud.om.DefaultDao;
import com.google.common.collect.Maps;

public class GeneratedDaoEmitter extends AbstractEmitter {
  @Override
  public Emission emit(final Model model) {
    final Map<String, Object> context = Maps.newHashMap();
    context.put("package", model.getEntityClassPackageName() + ".generated");
    context.put("entityClassName", model.getEntityClassName());
    context.put("daoClassName", DefaultDao.class.getName());
    context.put("entityClassSimpleName", model.getEntityClassSimpleName());
    context.put("model", model);
    context.put("finderClassName", AbstractFinder.class.getName());
    final String filename = context.get("package") + ".Generated" + context.get("entityClassSimpleName") + "Dao";
    return template("GeneratedDao.mustache.java", context, filename);
  }
}
