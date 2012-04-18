package com.github.jasonrose.crud.scanner;

import java.beans.PropertyDescriptor;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Validator;

import com.github.jasonrose.crud.om.DefaultDao;
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

public class EntityDefaultDaoEmitter implements Emitter {

  @Override
  public Emission emit(final Model model) {
    Writer out = null;
    Emission output = null;
    try {
      final InputSupplier<InputStreamReader> supplier = Resources.newReaderSupplier(getClass().getClassLoader().getResource("EntityDefaultDao.mustache.java"), Charsets.UTF_8);
      final String template = CharStreams.toString(supplier);

      final Mustache mustache = new MustacheBuilder().parse(template, "EntityDefaultDao.mustache.java");

      out = new StringWriter();

      final Set<Map<String, Object>> imports = Sets.newHashSet();
      final Set<Map<String, Object>> relationships = Sets.newHashSet();
      final Map<String, Object> context = Maps.newHashMap();
      context.put("package", model.getEntityClassPackageName() + ".generated");
      context.put("entityClassName", model.getEntityClassSimpleName());
      context.put("imports", imports);
      context.put("relationships", relationships);

      imports.add(createImport(model.getEntityClassName()));
      imports.add(createImport(DefaultDao.class.getName()));
      imports.add(createImport(Validator.class.getName()));
      for( final PropertyDescriptor relationshipDescriptor : model.getRelationships() ) {
        final Map<String, Object> relationship = Maps.newHashMap();
        relationships.add(relationship);
        final Class<?> propertyClass = relationshipDescriptor.getPropertyType();
        Class<?> targetEntityType = propertyClass;
        if( Collection.class.isAssignableFrom(propertyClass) ) {
          final ParameterizedType stringListType = (ParameterizedType) relationshipDescriptor.getReadMethod().getGenericReturnType();
          targetEntityType = (Class<?>) stringListType.getActualTypeArguments()[0];
        }

        final String methodName;
        final String returnType;
        if( relationshipDescriptor.getReadMethod().getAnnotation(ManyToMany.class) != null || relationshipDescriptor.getReadMethod().getAnnotation(ManyToOne.class) != null ) {
          methodName = String.format("get%ssBy%s", model.getEntityClassSimpleName(), targetEntityType.getSimpleName());
          returnType = "Set<" + model.getEntityClassSimpleName() + ">";
          imports.add(createImport(Set.class.getName()));
        } else if( relationshipDescriptor.getReadMethod().getAnnotation(OneToMany.class) != null || relationshipDescriptor.getReadMethod().getAnnotation(OneToOne.class) != null ) {
          methodName = String.format("get%sBy%s", model.getEntityClassSimpleName(), targetEntityType.getSimpleName());
          returnType = model.getEntityClassSimpleName();
        } else {
          throw new IllegalStateException("Unable to figure out the relationship type of " + relationshipDescriptor.getReadMethod().getName());
        }
        relationship.put("methodName", methodName);
        relationship.put("returnType", returnType);
      }

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