package com.github.jasonrose.crud.scanner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Map;

import javax.inject.Inject;
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

import com.github.jasonrose.crud.om.DefaultResource;
import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.common.io.Resources;
import com.sampullara.mustache.Mustache;
import com.sampullara.mustache.MustacheBuilder;
import com.sampullara.mustache.MustacheException;

public class EntityDefaultResourceEmitter extends AbstractEmitter {
  
  private final SourceGenerator sourceGenerator; 
  
  public EntityDefaultResourceEmitter(SourceGenerator sourceGenerator) {
    this.sourceGenerator = sourceGenerator;
  }

  @Override
  public Emission emit(final Model model) {
    Writer out = null;
    Emission output = null;
    try {
      final InputSupplier<InputStreamReader> supplier = Resources.newReaderSupplier(getClass().getClassLoader().getResource("EntityDefaultResource.mustache.java"), Charsets.UTF_8);
      final String template = CharStreams.toString(supplier);

      final Mustache mustache = new MustacheBuilder().parse(template, "EntityDefaultResource.mustache.java");

      out = new StringWriter();

      final Map<String, Object> context = Maps.newHashMap();
      final String packageName = model.getEntityClassPackageName() + ".generated";
      context.put("package", packageName);
      context.put("entityClassName", model.getEntityClassSimpleName());
      context.put("imports", generateImports(model, packageName));

      mustache.execute(out, context);
      output = new Emission(packageName + "." + model.getEntityClassSimpleName() + "DefaultResource", out.toString());
    } catch( IOException ioe ) {
      Throwables.propagate(ioe);
    } catch( MustacheException me ) {
      Throwables.propagate(me);
    }
    return output;
  }

  @SuppressWarnings("unchecked")
  private ImmutableSet<Map<String, String>> generateImports(final Model model, final String packageName) {
    return ImmutableSet.copyOf(Iterables.concat(
        Iterables.transform(Arrays.asList(DefaultResource.class,
            Path.class,
            PUT.class,
            Produces.class,
            Consumes.class,
            Response.class,
            Context.class,
            UriInfo.class,
            PathParam.class,
            POST.class,
            Inject.class,
            MediaType.class), sourceGenerator.createClassToImportFunction()),
        Iterables.transform(Arrays.asList(model.getEntityClassName(),
            String.format("%s.%s%s", packageName, model.getEntityClassSimpleName(), "Dao")), sourceGenerator.createClassNameToImportFunction())));
  }
}