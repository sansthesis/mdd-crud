package com.github.jasonrose.crud.scanner;

import java.util.Map;

import com.google.common.base.Function;

public interface SourceGenerator {
  Map<String, String> createImport(String className);
  Map<String, String> createImport(Class<?> clazz);
  Function<Class<?>, Map<String, String>> createClassToImportFunction();
  Function<String, Map<String, String>> createClassNameToImportFunction();
}
