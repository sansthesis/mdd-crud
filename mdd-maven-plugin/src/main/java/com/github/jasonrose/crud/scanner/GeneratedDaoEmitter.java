package com.github.jasonrose.crud.scanner;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.github.jasonrose.crud.om.AbstractFinder;
import com.github.jasonrose.crud.om.AbstractFinder.Pred;
import com.github.jasonrose.crud.om.DefaultDao;
import com.github.jasonrose.crud.om.Preds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class GeneratedDaoEmitter extends AbstractEmitter {
  @Override
  public Emission emit(final Model model) {
    final Map<String, Object> context = Maps.newHashMap();
    context.put("package", model.getEntityClassPackageName() + ".generated");
    context.put("entityClassName", model.getEntityClassName());
    context.put("daoClassName", DefaultDao.class.getName());
    context.put("entityClassSimpleName", model.getEntityClassSimpleName());
    context.put("model", model);
    context.put("preds", Preds.class.getName());
    context.put("pred", String.format("%s.%s", AbstractFinder.class.getName(), Pred.class.getSimpleName()));
    context.put("finderClassName", AbstractFinder.class.getName());
    final Map<String, Object> relationships = Maps.newTreeMap();
    for( final PropertyDescriptor relationshipDescriptor : model.getRelationships() ) {
      final Class<?> type = findEntityType(relationshipDescriptor.getReadMethod());
      relationships.put(relationshipDescriptor.getName(), findPrimaryKeyType(type));
    }
    context.put("relationships", relationships.entrySet());
    final String filename = context.get("package") + ".Generated" + context.get("entityClassSimpleName") + "Dao";
    return template("GeneratedDao.mustache.java", context, filename);
  }
  
  private Class<?> findEntityType(final Method readMethod) {
    final Class<?> inputType = readMethod.getReturnType();
    if( inputType.isAnnotationPresent(Entity.class) ) {
      return inputType;
    } else if( Collection.class.isAssignableFrom(inputType) ) {
      Type type = readMethod.getGenericReturnType();
      Type actualType = ((ParameterizedType) type).getActualTypeArguments()[0];
      return (Class<?>) actualType;
    } else {
      throw new IllegalStateException("Unprocessable type: " + inputType.getName());
    }
  }
  
  private Class<?> findPrimaryKeyType(final Class<?> inputType) {
    Class<?> output = null;
    for( final Method method : inputType.getMethods() ) {
      if( method.isAnnotationPresent(Id.class) ) {
        output = method.getReturnType();
      }
    }
    for( final Field field : inputType.getFields() ) {
      if( field.isAnnotationPresent(Id.class) ) {
        output = field.getType();
      }
    }
    if( output == null ) {
      throw new IllegalStateException("Unable to find an @Id on type: " + inputType.getName());
    }
    return output;
  }
}
