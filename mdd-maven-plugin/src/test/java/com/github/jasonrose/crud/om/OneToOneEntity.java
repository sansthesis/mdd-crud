package com.github.jasonrose.crud.om;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class OneToOneEntity extends AbstractEntity {

  @NotNull
  @Size(min = 1, max = 255)
  public String getSomething() {
    return getAndCoerce("something");
  }

  public void setSomething(final String value) {
    put("something", value);
  }

  @OneToOne
  @NotNull
  public OneToOneEntity getOneToOne() {
    return getAndCoerce("oneToOne");
  }

  public void setOneToOne(final OneToOneEntity value) {
    put("oneToOne", value);
  }

}
