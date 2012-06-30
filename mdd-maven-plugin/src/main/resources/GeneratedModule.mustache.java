package {{{package}}};

public class GeneratedModule extends {{{moduleClassName}}} {
  @Override
  protected void configure() {
    {{#bindings}}
    bind(new {{{typeLiteralClassName}}}<{{{serviceClassName}}}<{{{entityClassName}}}>>() {}).to({{{implementation}}}.class);
    {{/bindings}}
  }
}
