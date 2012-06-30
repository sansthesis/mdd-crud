package {{{package}}};

public class Generated{{{entityClassSimpleName}}}Service extends {{{serviceClassName}}}<{{{entityClassName}}}, {{{daoClassName}}}<{{{entityClassName}}}>, {{{authorizerClassName}}}<{{{entityClassName}}}>, {{{validatorClassName}}}<{{{entityClassName}}}>> {

  @javax.inject.Inject
  public Generated{{{entityClassSimpleName}}}Service(final {{{daoClassName}}}<{{{entityClassName}}}> dao, final {{{authorizerClassName}}}<{{{entityClassName}}}> authorizer, final {{{validatorClassName}}}<{{{entityClassName}}}> validator) {
    super(dao, authorizer, validator);
  }
}
