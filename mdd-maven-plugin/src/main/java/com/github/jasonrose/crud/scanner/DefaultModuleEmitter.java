package com.github.jasonrose.crud.scanner;

import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.common.io.Resources;
import com.google.inject.AbstractModule;
import com.sampullara.mustache.Mustache;
import com.sampullara.mustache.MustacheBuilder;

public class DefaultModuleEmitter extends AbstractEmitter {

  @Override
  public Emission emit(final Collection<Model> models) {
    Writer out = null;
    Emission output = null;
    try {
      final InputSupplier<InputStreamReader> supplier = Resources.newReaderSupplier(getClass().getClassLoader().getResource("DefaultModule.mustache.java"), Charsets.UTF_8);
      final String template = CharStreams.toString(supplier);

      final Mustache mustache = new MustacheBuilder().parse(template, "DefaultModule.mustache.java");

      out = new StringWriter();

      final Set<Map<String, Object>> imports = Sets.newHashSet();
      final Map<String, Object> context = Maps.newHashMap();
      final Set<Map<String, String>> bindings = Sets.newHashSet();
      context.put("bindings", bindings);
      context.put("imports", imports);
      imports.add(createImport(AbstractModule.class.getName()));
      for( final Model model : models ) {
        context.put("package", model.getEntityClassPackageName() + ".generated");

        bindings.add(createBinding(model.getEntityClassSimpleName() + "Dao", model.getEntityClassSimpleName() + "DefaultDao"));
        imports.add(createImport(String.format("%s.%s%s", context.get("package"), model.getEntityClassSimpleName(), "Dao")));
        imports.add(createImport(String.format("%s.%s%s", context.get("package"), model.getEntityClassSimpleName(), "DefaultDao")));
      }
      mustache.execute(out, context);
      output = new Emission(context.get("package") + ".DefaultModule", out.toString());
    } catch( final Exception e ) {
      Throwables.propagate(e);
    }
    return output;
  }

  private Map<String, String> createBinding(final String interfaceName, final String implementationName) {
    final Map<String, String> output = ImmutableMap.of("interface", interfaceName, "implementation", implementationName);
    return output;
  }

  private Map<String, Object> createImport(final Object value) {
    final Map<String, Object> output = ImmutableMap.of("import", value);
    return output;
  }
}