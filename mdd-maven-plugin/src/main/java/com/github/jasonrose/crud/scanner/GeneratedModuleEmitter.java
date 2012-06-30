package com.github.jasonrose.crud.scanner;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.github.jasonrose.crud.om.Dao;
import com.github.jasonrose.crud.om.Service;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class GeneratedModuleEmitter extends AbstractEmitter {

  @Override
  public Emission emit(final Collection<Model> models) {
    final Map<String, Object> context = Maps.newHashMap();
    context.put("moduleClassName", AbstractModule.class.getName());
    final Set<Map<String, String>> bindings = Sets.newLinkedHashSet();
    context.put("bindings", bindings);
    
    for( final Model model : models ) {
      final String packageName = model.getEntityClassPackageName() + ".generated";
      context.put("package", packageName);
      
      bindings.add(createBinding(Dao.class.getName(), model.getEntityClassName(), packageName + ".Generated" + model.getEntityClassSimpleName() + "Dao"));
      bindings.add(createBinding(Service.class.getName(), model.getEntityClassName(), packageName + ".Generated" + model.getEntityClassSimpleName() + "Service"));
    }
    final String filename = context.get("package") + ".GeneratedModule";
    return template("GeneratedModule.mustache.java", context, filename);
  }

  private Map<String, String> createBinding(final String serviceClassName, final String typeName, final String implementationName) {
    return ImmutableMap.of("serviceClassName", serviceClassName, "entityClassName", typeName, "implementation", implementationName, "typeLiteralClassName", TypeLiteral.class.getName());
  }
}
