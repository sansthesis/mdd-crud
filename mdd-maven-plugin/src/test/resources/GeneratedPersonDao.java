package com.github.jasonrose.crud.om.generated;

public class GeneratedPersonDao extends com.github.jasonrose.crud.om.DefaultDao<com.github.jasonrose.crud.om.Person> {

  @javax.inject.Inject
  public GeneratedPersonDao(final javax.persistence.EntityManager em) {
    super(com.github.jasonrose.crud.om.Person.class, em);
  }

  @Override
  public Finder finder() {
    return new Finder(this);
  }

  public static class Finder extends com.github.jasonrose.crud.om.AbstractFinder<com.github.jasonrose.crud.om.Person, GeneratedPersonDao, Finder> {

    public Finder(final GeneratedPersonDao dao) {
      super(dao);
    }

    public Finder id(final java.lang.Long value) {
      return helper("id", value);
    }

    public Finder something(final java.lang.String value) {
      return helper("something", value);
    }

    @Override
    protected Finder getThis() {
      return this;
    }
  }
}
