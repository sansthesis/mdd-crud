package com.github.jasonrose.crud.scanner;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.github.jasonrose.crud.om.Dao;
import com.github.jasonrose.crud.om.DefaultDao;
import com.github.jasonrose.crud.om.DefaultService;
import com.github.jasonrose.crud.om.Service;
import com.github.jasonrose.crud.representation.Representer;
import com.github.jasonrose.crud.representation.spi.DefaultRepresenter;
import com.github.jasonrose.crud.security.Authorizer;
import com.github.jasonrose.crud.security.spi.NoOpAuthorizerImpl;
import com.github.jasonrose.crud.validation.Validator;
import com.github.jasonrose.crud.validation.spi.NoOpValidatorImpl;
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
      final String baseGeneratedTypeString = packageName + ".Generated" + model.getEntityClassSimpleName();

      bindings.add(createBinding(Dao.class.getName(), model.getEntityClassName(), createTypeLiteralTypeString(TypeLiteral.class.getName(), DefaultDao.class.getName(), model.getEntityClassName())));
      bindings.add(createBinding(Service.class.getName(), model.getEntityClassName(), createTypeLiteralTypeString(TypeLiteral.class.getName(), DefaultService.class.getName(), createServiceParameterizedTypes(model.getEntityClassName()))));
      bindings.add(createBinding(Validator.class.getName(), model.getEntityClassName(), createTypeLiteralTypeString(TypeLiteral.class.getName(), NoOpValidatorImpl.class.getName(), model.getEntityClassName())));
      bindings.add(createBinding(Authorizer.class.getName(), model.getEntityClassName(), createTypeLiteralTypeString(TypeLiteral.class.getName(), NoOpAuthorizerImpl.class.getName(), model.getEntityClassName())));
      bindings.add(createBinding(Representer.class.getName(), model.getEntityClassName(), createTypeLiteralTypeString(TypeLiteral.class.getName(), DefaultRepresenter.class.getName(), model.getEntityClassName())));
      bindings.add(createBinding(baseGeneratedTypeString + "Resource.class", baseGeneratedTypeString + "ResourceImpl.class"));
    }
    final String filename = context.get("package") + ".GeneratedModule";
    return template("GeneratedModule.mustache.java", context, filename);
  }

  private String createServiceParameterizedTypes(String entityClassName) {
    return String.format("%s, %s<%s>, %s<%s>, %s<%s>", entityClassName, Dao.class.getName(), entityClassName, Authorizer.class.getName(), entityClassName, Validator.class.getName(), entityClassName);
  }

  private Map<String, String> createBinding(final String from, final String to) {
    return ImmutableMap.of("from", from, "to", to);
  }

  private Map<String, String> createBinding(final String serviceClassName, final String typeName, final String implementationName) {
    return createBinding(String.format("new %s<%s<%s>>() {}", TypeLiteral.class.getName(), serviceClassName, typeName), implementationName);
  }

  private String createTypeLiteralTypeString(final String wrapperClassName, final String implementationClassName, final String entityClassName) {
    return String.format("new %s<%s<%s>>() {}", wrapperClassName, implementationClassName, entityClassName);
  }
}
