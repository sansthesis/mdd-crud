package {{{package}}};

public class Generated{{{entityClassSimpleName}}}Dao extends {{{daoClassName}}}<{{{entityClassName}}}> {

  @javax.inject.Inject
  public Generated{{{entityClassSimpleName}}}Dao(final javax.persistence.EntityManager em) {
    super({{{entityClassName}}}.class, em);
  }

  @Override
  public Finder finder() {
    return new Finder(this);
  }

  public static class Finder extends {{{finderClassName}}}<{{{entityClassName}}}, Generated{{{entityClassSimpleName}}}Dao, Finder> {

    public Finder(final Generated{{{entityClassSimpleName}}}Dao dao) {
      super(dao);
    }
{{#model.properties}}

    public Finder {{name}}(final {{propertyType.name}} value) {
      return helper("{{name}}", value);
    }
{{/model.properties}}

    @Override
    protected Finder getThis() {
      return this;
    }
  }
}
