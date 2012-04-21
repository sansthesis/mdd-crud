package {{{package}}};

{{#imports}}
import {{{import}}};
{{/imports}}

public class {{{entityClassName}}}DefaultDao extends DefaultDao<{{{entityClassName}}}> implements {{{entityClassName}}}Dao {

  public {{{entityClassName}}}DefaultDao() {
    this({{{entityClassName}}}.class);
  }

  public {{{entityClassName}}}DefaultDao(final Class<? extends {{{entityClassName}}}> entityClass) {
    super(entityClass);
  }
{{#relationships}}

  @Override
  public {{{returnType}}} {{{methodName}}}(final Long id) {
    return {{{getterMethodName}}}("{{{propertyName}}}", id);
  }
{{/relationships}}
}
