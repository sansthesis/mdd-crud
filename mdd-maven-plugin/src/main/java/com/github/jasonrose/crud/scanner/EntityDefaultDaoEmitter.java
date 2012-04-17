package com.github.jasonrose.crud.scanner;

import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Set;

import com.github.jasonrose.crud.scanner.stubs.DefaultDao;
import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.common.io.Resources;
import com.sampullara.mustache.Mustache;
import com.sampullara.mustache.MustacheBuilder;

public class EntityDefaultDaoEmitter {
  public Emission emit(final Model model) {
    Writer out = null;
    Emission output = null;
    try {
      final InputSupplier<InputStreamReader> supplier = Resources.newReaderSupplier(getClass().getClassLoader().getResource("EntityDefaultDao.mustache.java"), Charsets.UTF_8);
      final String template = CharStreams.toString(supplier);

      final Mustache mustache = new MustacheBuilder().parse(template, "EntityDefaultDao.mustache.java");

      out = new StringWriter();

      final Set<Map<String, Object>> imports = Sets.newHashSet();
      final Map<String, Object> context = Maps.newHashMap();
      context.put("package", model.getEntityClassPackageName() + ".generated");
      context.put("entityClassName", model.getEntityClassSimpleName());
      context.put("imports", imports);

      imports.add(createImport(model.getEntityClassName()));
      imports.add(createImport(DefaultDao.class.getName()));

      mustache.execute(out, context);
      output = new Emission(context.get("package") + "." + context.get("entityClassName") + "DefaultDao", out.toString());
    } catch( final Exception e ) {
      Throwables.propagate(e);
    }
    return output;
  }

  private Map<String, Object> createImport(final Object value) {
    final Map<String, Object> output = ImmutableMap.of("import", value);
    return output;
  }
}