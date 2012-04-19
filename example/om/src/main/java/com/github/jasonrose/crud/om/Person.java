package com.github.jasonrose.crud.om;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person extends AbstractEntity {

  public Person() {
    setChilds(new HashSet<Person>());
    setFriends(new HashSet<Person>());
  }

  @OneToMany
  @NotNull
  public Set<Person> getChilds() {
    return getAndCoerce("childs");
  }

  @ManyToMany
  @NotNull
  public Set<Person> getFriends() {
    return getAndCoerce("friends");
  }

  @ManyToOne
  @NotNull
  public Person getParent() {
    return getAndCoerce("parent");
  }

  @NotNull
  @Size(min = 1, max = 255)
  public String getSomething() {
    return getAndCoerce("something");
  }

  @OneToOne
  @NotNull
  public Person getSpouse() {
    return getAndCoerce("spouse");
  }

  public void setChilds(final Set<Person> value) {
    put("childs", value);
  }

  public void setFriends(final Set<Person> value) {
    put("friends", value);
  }

  public void setParent(final Person value) {
    put("parent", value);
  }

  public void setSomething(final String value) {
    put("something", value);
  }

  public void setSpouse(final Person value) {
    put("spouse", value);
  }

}
