package com.github.jasonrose.crud.om;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Division extends AbstractEntity {

  public Division() {
    setContacts(new HashSet<Contact>());
  }

  @NotNull
  @ManyToMany
  public Set<Contact> getContacts() {
    return getAndCoerce("contacts");
  }

  @NotNull
  @Size(min = 1, max = 255)
  public String getName() {
    return getAndCoerce("name");
  }

  @NotNull
  @Size(min = 1, max = 255)
  public String getNumber() {
    return getAndCoerce("number");
  }

  public void setContacts(final Set<Contact> contacts) {
    put("contacts", contacts);
  }

  public void setName(final String value) {
    put("name", value);
  }

  public void setNumber(final String value) {
    put("number", value);
  }

}
