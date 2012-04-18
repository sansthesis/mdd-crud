package com.github.jasonrose.crud.om;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ManyToOneEntity extends AbstractEntity {

  @NotNull
  @Size(min = 1, max = 255)
  public String getSomething() {
    return getAndCoerce("something");
  }

  public void setSomething(final String value) {
    put("something", value);
  }

  @ManyToOne
  @NotNull
  public OneToManyEntity getOneToMany() {
    return getAndCoerce("oneToMany");
  }

  public void setOneToMany(final OneToManyEntity value) {
    put("oneToMany", value);
  }

}
