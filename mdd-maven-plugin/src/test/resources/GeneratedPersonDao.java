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

    protected Finder(final GeneratedPersonDao dao) {
      super(dao);
    }

    public Finder id(final java.lang.Long value) {
      return helper("id", com.github.jasonrose.crud.om.Preds.eq(value));
    }

    public Finder id(final com.github.jasonrose.crud.om.AbstractFinder.Pred<java.lang.Long> value) {
      return helper("id", value);
    }

    public Finder something(final java.lang.String value) {
      return helper("something", com.github.jasonrose.crud.om.Preds.eq(value));
    }

    public Finder something(final com.github.jasonrose.crud.om.AbstractFinder.Pred<java.lang.String> value) {
      return helper("something", value);
    }

    public Finder childs(final java.lang.Long value) {
      return relationshipHelper("childs", com.github.jasonrose.crud.om.Preds.eq(value));
    }

    public Finder childs(final com.github.jasonrose.crud.om.AbstractFinder.Pred<java.lang.Long> value) {
      return relationshipHelper("childs", value);
    }

    public Finder friends(final java.lang.Long value) {
      return relationshipHelper("friends", com.github.jasonrose.crud.om.Preds.eq(value));
    }

    public Finder friends(final com.github.jasonrose.crud.om.AbstractFinder.Pred<java.lang.Long> value) {
      return relationshipHelper("friends", value);
    }

    public Finder parent(final java.lang.Long value) {
      return relationshipHelper("parent", com.github.jasonrose.crud.om.Preds.eq(value));
    }

    public Finder parent(final com.github.jasonrose.crud.om.AbstractFinder.Pred<java.lang.Long> value) {
      return relationshipHelper("parent", value);
    }

    public Finder spouse(final java.lang.Long value) {
      return relationshipHelper("spouse", com.github.jasonrose.crud.om.Preds.eq(value));
    }

    public Finder spouse(final com.github.jasonrose.crud.om.AbstractFinder.Pred<java.lang.Long> value) {
      return relationshipHelper("spouse", value);
    }
  }
}
