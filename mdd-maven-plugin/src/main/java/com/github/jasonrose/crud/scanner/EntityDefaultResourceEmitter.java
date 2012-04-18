package com.github.jasonrose.crud.scanner;

import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.github.jasonrose.crud.om.Dao;
import com.github.jasonrose.crud.om.DefaultResource;
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

public class EntityDefaultResourceEmitter implements Emitter {

  @Override
  public Emission emit(final Model model) {
    Writer out = null;
    Emission output = null;
    try {
      final InputSupplier<InputStreamReader> supplier = Resources.newReaderSupplier(getClass().getClassLoader().getResource("EntityDefaultResource.mustache.java"), Charsets.UTF_8);
      final String template = CharStreams.toString(supplier);

      final Mustache mustache = new MustacheBuilder().parse(template, "EntityDefaultResource.mustache.java");

      out = new StringWriter();

      final Set<Map<String, Object>> imports = Sets.newHashSet();
      final Map<String, Object> context = Maps.newHashMap();
      context.put("package", model.getEntityClassPackageName() + ".generated");
      context.put("entityClassName", model.getEntityClassSimpleName());
      context.put("imports", imports);

      imports.add(createImport(model.getEntityClassName()));
      imports.add(createImport(DefaultResource.class.getName()));
      imports.add(createImport(Dao.class.getName()));
      imports.add(createImport(Path.class.getName()));
      imports.add(createImport(PUT.class.getName()));
      imports.add(createImport(Produces.class.getName()));
      imports.add(createImport(Consumes.class.getName()));
      imports.add(createImport(Response.class.getName()));
      imports.add(createImport(Context.class.getName()));
      imports.add(createImport(UriInfo.class.getName()));
      imports.add(createImport(PathParam.class.getName()));
      imports.add(createImport(POST.class.getName()));
      imports.add(createImport(MediaType.class.getName()));

      mustache.execute(out, context);
      output = new Emission(context.get("package") + "." + context.get("entityClassName") + "DefaultResource", out.toString());
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