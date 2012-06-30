package {{{package}}};

public class Generated{{{entityClassSimpleName}}}Dao extends {{{daoClassName}}}<{{{entityClassName}}}> {

  @javax.inject.Inject
  public Generated{{{entityClassSimpleName}}}Dao(final javax.persistence.EntityManager em) {
    super({{{entityClassName}}}.class, em);
  }
}
