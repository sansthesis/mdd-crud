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
  public {{{returnType}}} {{{methodName}}}(Long id) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
  {{/relationships}}
}
