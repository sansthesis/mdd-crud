package com.github.jasonrose.crud.om;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Contact extends AbstractEntity {

  public Contact() {
    setDivisions(new HashSet<Division>());
  }

  @ManyToMany(mappedBy = "contacts")
  @NotNull
  public Set<Division> getDivisions() {
    return getAndCoerce("divisions");
  }

  @NotNull
  @Size(min = 1, max = 255)
  public String getEmail() {
    return getAndCoerce("email");
  }

  @NotNull
  @Size(min = 1, max = 255)
  public String getFirstName() {
    return getAndCoerce("firstName");
  }

  @NotNull
  @Size(min = 1, max = 255)
  public String getLastName() {
    return getAndCoerce("lastName");
  }

  @Size(max = 20)
  public String getZipCode() {
    return getAndCoerce("zipCode");
  }

  public void setDivisions(final Set<Division> value) {
    put("divisions", value);
  }

  public void setEmail(final String value) {
    put("email", value);
  }

  public void setFirstName(final String value) {
    put("firstName", value);
  }

  public void setLastName(final String value) {
    put("lastName", value);
  }

  public void setZipCode(final String value) {
    put("zipCode", value);
  }

}
