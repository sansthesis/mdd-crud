package com.github.jasonrose.crud.om;

import java.util.Date;
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

  public String getNullableProperty() {
    return getAndCoerce("nullableProperty");
  }

  public Date getDateProperty() {
    return getAndCoerce("dateProperty");
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

  public void setNullableProperty(final String value) {
    put("nullableProperty", value);
  }

  public void setDateProperty(final Date value) {
    put("dateProperty", value);
  }

}
