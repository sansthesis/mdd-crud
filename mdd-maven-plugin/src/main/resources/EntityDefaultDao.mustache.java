package {{{package}}};

{{#imports}}
import {{{import}}};
{{/imports}}

public class {{{entityClassName}}}DefaultDao extends DefaultDao<{{{entityClassName}}}> implements {{{entityClassName}}}Dao {
  public {{{entityClassName}}}DefaultDao(final Validator validator) {
    super(validator);
  }

  public {{{entityClassName}}}DefaultDao() {
    super();
  }
  {{#relationships}}
  
  @Override
  public {{{returnType}}} {{{methodName}}}(Long id) {
    return {{{getterMethodName}}}("{{{propertyName}}}", id);
  }
  {{/relationships}}
}
