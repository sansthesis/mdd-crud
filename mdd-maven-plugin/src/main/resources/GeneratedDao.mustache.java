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

    protected Finder(final Generated{{{entityClassSimpleName}}}Dao dao) {
      super(dao);
    }
{{#model.properties}}

    public Finder {{name}}(final {{propertyType.name}} value) {
      return helper("{{name}}", {{preds}}.eq(value));
    }

    public Finder {{name}}(final {{pred}}<{{propertyType.name}}> value) {
      return helper("{{name}}", value);
    }
{{/model.properties}}
{{#relationships}}

    public Finder {{key}}(final {{value.name}} value) {
      return relationshipHelper("{{key}}", {{preds}}.eq(value));
    }

    public Finder {{key}}(final {{pred}}<{{value.name}}> value) {
      return relationshipHelper("{{key}}", value);
    }
{{/relationships}}
  }
}
