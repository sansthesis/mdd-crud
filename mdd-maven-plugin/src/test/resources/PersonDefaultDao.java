package com.github.jasonrose.crud.om.generated;

import java.util.Set;
import com.github.jasonrose.crud.om.DefaultDao;
import com.github.jasonrose.crud.om.Person;

public class PersonDefaultDao extends DefaultDao<Person> implements PersonDao {

  public PersonDefaultDao() {
    this(Person.class);
  }

  public PersonDefaultDao(final Class<? extends Person> entityClass) {
    super(entityClass);
  }

  @Override
  public Set<Person> getPersonsByParent(final Long id) {
    return getByManyRelationship("parent", id);
  }

  @Override
  public Person getPersonBySpouse(final Long id) {
    return getByOneRelationship("spouse", id);
  }

  @Override
  public Set<Person> getPersonsByFriend(final Long id) {
    return getByManyRelationship("friends", id);
  }

  @Override
  public Person getPersonByChild(final Long id) {
    return getByOneRelationship("childs", id);
  }
}
