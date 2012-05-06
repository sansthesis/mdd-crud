package com.github.jasonrose.crud.scanner;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.github.jasonrose.crud.om.Dao;
import com.github.jasonrose.functional.Functional;
import com.google.common.base.Function;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sampullara.mustache.Mustache;
import com.sampullara.mustache.MustacheException;

public class EntityDaoEmitter extends AbstractEmitter {
  private final Functional functional;

  public EntityDaoEmitter(final SourceGenerator sourceGenerator, final Functional functional) {
    super(sourceGenerator);
    this.functional = functional;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Emission emit(final Model model) {
    Emission output = null;
    try {
      final Mustache mustache = createMustacheTemplate("EntityDao.mustache.java");

      final Map<String, Object> context = generateContext(model);
      final Set<Map<String, String>> imports = (Set<Map<String, String>>) context.get("imports");
      imports.add(getSourceGenerator().createImport(Dao.class));

      final Writer out = new StringWriter();
      mustache.execute(out, context);
      output = new Emission(context.get("package") + "." + context.get("entityClassName") + "Dao", out.toString());
    } catch( IOException ioe ) {
      Throwables.propagate(ioe);
    } catch( MustacheException me ) {
      Throwables.propagate(me);
    }
    return output;
  }

  protected Map<String, Object> generateContext(final Model model) {
    final Set<Map<String, String>> imports = Sets.newHashSet();
    final Set<Map<String, Object>> relationships = getRelationships(model);
    final Map<String, Object> context = Maps.newHashMap();
    context.put("package", model.getEntityClassPackageName() + ".generated");
    context.put("entityClassName", model.getEntityClassSimpleName());
    context.put("imports", imports);
    context.put("relationships", relationships);

    imports.add(getSourceGenerator().createImport(model.getEntityClassName()));
    final Function<Map<String, Object>, Set<Map<String, String>>> plucked = functional.pluck("imports");
    for( final Set<Map<String, String>> importEntries : Iterables.transform(relationships, plucked) ) {
      imports.addAll(importEntries);
    }
    return context;
  }

  protected Set<Map<String, Object>> getRelationships(final Model model) {
    final Set<Map<String, Object>> relationships = Sets.newHashSet();
    // Add custom getter methods to fetch entities by their relationships' ids.
    for( final PropertyDescriptor relationshipDescriptor : model.getRelationships() ) {
      final Map<String, Object> relationship = buildRelationshipMapping(model, relationshipDescriptor);
      relationships.add(relationship);
    }
    return relationships;
  }

  protected Map<String, Object> buildRelationshipMapping(Model model, PropertyDescriptor relationshipDescriptor) {
    final Map<String, Object> relationship = Maps.newHashMap();
    final Set<Map<String, String>> imports = Sets.newHashSet();

    final String returnType;
    final String getterMethodName;
    final Method readMethod = relationshipDescriptor.getReadMethod();
    final boolean isManyTo = readMethod.isAnnotationPresent(ManyToMany.class) || readMethod.isAnnotationPresent(ManyToOne.class);
    final boolean isToMany = readMethod.isAnnotationPresent(ManyToMany.class) || readMethod.isAnnotationPresent(OneToMany.class);

    if( isManyTo ) {
      returnType = "Set<" + model.getEntityClassSimpleName() + ">";
      imports.add(getSourceGenerator().createImport(Set.class));
      getterMethodName = "getByManyRelationship";
    } else {
      returnType = model.getEntityClass().getSimpleName();
      getterMethodName = "getByOneRelationship";
    }
    
    final String methodName = getSourceGenerator().generateGetByRelationshipMethodName(model.getEntityClass().getSimpleName(), relationshipDescriptor.getName(), isManyTo, isToMany);
    relationship.put("methodName", methodName);
    relationship.put("returnType", returnType);
    relationship.put("getterMethodName", getterMethodName);
    relationship.put("propertyName", relationshipDescriptor.getName());
    relationship.put("imports", imports);
    return relationship;
  }
}
