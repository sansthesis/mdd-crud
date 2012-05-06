package com.github.jasonrose.crud.scanner;

import java.util.Locale;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

public class SourceGeneratorImpl implements SourceGenerator {

  @Override
  public Map<String, String> createImport(final String className) {
    return ImmutableMap.of("import", className);
  }

  @Override
  public Map<String, String> createImport(Class<?> clazz) {
    return createImport(clazz.getName());
  }
  
  @Override
  public Function<Class<?>, Map<String, String>> createClassToImportFunction() {
    return new Function<Class<?>, Map<String, String>>() {
      @Override
      public Map<String, String> apply(Class<?> input) {
        return createImport(input);
      }
    };
  }
  
  @Override
  public Function<String, Map<String, String>> createClassNameToImportFunction() {
    return new Function<String, Map<String, String>>() {
      @Override
      public Map<String, String> apply(String input) {
        return createImport(input);
      }
    };
  }

  @Override
  public String capitalize(String propertyName) {
    return propertyName.substring(0, 1).toUpperCase(Locale.US) + propertyName.substring(1);
  }

  @Override
  public String depluralize(String propertyName) {
    return propertyName.substring(0, propertyName.length() - 1);
  }
  
  @Override
  public String generateGetByRelationshipMethodName(String entityName, String relationshipName, boolean isManyTo, boolean isToMany) {
    final StringBuilder buffer = new StringBuilder("get").append(entityName);
    String formattedRelationshipName = capitalize(relationshipName);
    if( isManyTo ) {
      buffer.append("s");
    }
    buffer.append("By");
    if( isToMany) {
      formattedRelationshipName = depluralize(formattedRelationshipName);
    }
    buffer.append(formattedRelationshipName);
    return buffer.toString();
  }
}
