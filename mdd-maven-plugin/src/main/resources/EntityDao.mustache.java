package {{package}};

{{#imports}}
import {{import}};
{{/imports}}

public interface {{entityClassName}}Dao extends Dao<{{entityClassName}}> {
  {{#relationships}}
  
  {{{returnType}}} {{{methodName}}}(Long id);
  {{/relationships}}
}
