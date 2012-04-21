package {{{package}}};

{{#imports}}
import {{{import}}};
{{/imports}}

public class DefaultModule extends AbstractModule {
  @Override
  protected void configure() {
    {{#bindings}}
    bind({{{interface}}}.class).to({{{implementation}}}.class);
    {{/bindings}}
  }
}
