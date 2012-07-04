package {{{package}}};

public class Generated{{{entityClassSimpleName}}}ResourceImpl extends {{{resourceClassName}}}<{{{entityClassName}}}, {{{serviceClassName}}}<{{{entityClassName}}}>, {{{representerClassName}}}<{{{entityClassName}}}>> implements Generated{{{entityClassSimpleName}}}Resource {

  @javax.inject.Inject
  public Generated{{{entityClassSimpleName}}}ResourceImpl(final {{{serviceClassName}}}<{{{entityClassName}}}> service, final {{{representerClassName}}}<{{{entityClassName}}}> representer) {
    super(service, representer);
  }
}
