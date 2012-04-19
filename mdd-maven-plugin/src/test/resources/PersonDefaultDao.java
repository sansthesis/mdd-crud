package com.github.jasonrose.crud.om.generated;

import java.util.Set;
import com.github.jasonrose.crud.om.DefaultDao;
import javax.validation.Validator;
import com.github.jasonrose.crud.om.Person;

public class PersonDefaultDao extends DefaultDao<Person> implements PersonDao {
  public PersonDefaultDao(final Validator validator) {
    super(validator);
  }

  public PersonDefaultDao() {
    super();
  }
  
  public Person getPersonBySpouse(Long id) {
    return getByOneRelationship("spouse", id);
  }
  
  public Set<Person> getPersonsByParent(Long id) {
    return getByManyRelationship("parent", id);
  }
  
  public Set<Person> getPersonsByFriend(Long id) {
    return getByManyRelationship("friends", id);
  }
  
  public Person getPersonByChild(Long id) {
    return getByOneRelationship("childs", id);
  }
}
