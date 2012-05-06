package com.github.jasonrose.crud.scanner;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.AbstractModule;
import com.sampullara.mustache.Mustache;
import com.sampullara.mustache.MustacheException;

public class DefaultModuleEmitter extends AbstractEmitter {
  
  private final SourceGenerator sourceGenerator;

  public DefaultModuleEmitter(SourceGenerator sourceGenerator) {
    this.sourceGenerator = sourceGenerator;
  }

  @Override
  public Emission emit(final Collection<Model> models) {
    Emission output = null;
    try {
      final Mustache mustache = createMustacheTemplate("DefaultModule.mustache.java");

      final Set<Map<String, String>> imports = Sets.newHashSet();
      final Map<String, Object> context = Maps.newHashMap();
      final Set<Map<String, String>> bindings = Sets.newHashSet();
      context.put("bindings", bindings);
      context.put("imports", imports);
      imports.add(sourceGenerator.createImport(AbstractModule.class));
      for( final Model model : models ) {
        final String packageName = model.getEntityClassPackageName() + ".generated";
        context.put("package", packageName);

        bindings.add(createBinding(model.getEntityClassSimpleName() + "Dao", model.getEntityClassSimpleName() + "DefaultDao"));
        imports.add(sourceGenerator.createImport(String.format("%s.%s%s", packageName, model.getEntityClassSimpleName(), "Dao")));
        imports.add(sourceGenerator.createImport(String.format("%s.%s%s", packageName, model.getEntityClassSimpleName(), "DefaultDao")));
      }
      final Writer out = new StringWriter();
      mustache.execute(out, context);
      output = new Emission(context.get("package") + ".DefaultModule", out.toString());
    } catch( IOException ioe ) {
      Throwables.propagate(ioe);
    } catch( MustacheException e ) {
      Throwables.propagate(e);
    }
    return output;
  }

  /**
   * Creates a Guice binding of an interface to an implementation.
   * @param interfaceName The class name of the interface.
   * @param implementationName The class name of the implementation.
   * @return A Map of interface name to implementation name.
   */
  private Map<String, String> createBinding(final String interfaceName, final String implementationName) {
    return ImmutableMap.of("interface", interfaceName, "implementation", implementationName);
  }
}
