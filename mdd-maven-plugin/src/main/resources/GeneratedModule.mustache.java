package {{{package}}};

public class GeneratedModule extends {{{moduleClassName}}} {
  @Override
  protected void configure() {
    {{#bindings}}
    bind({{{from}}}).to({{{to}}});
    {{/bindings}}
  }
}
