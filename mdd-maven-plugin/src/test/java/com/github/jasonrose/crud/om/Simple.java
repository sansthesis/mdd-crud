package com.github.jasonrose.crud.om;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Simple extends AbstractEntity {

  @NotNull
  @Size(min = 1, max = 255)
  public String getSomething() {
    return getAndCoerce("something");
  }

  public void setSomething(final String value) {
    put("something", value);
  }

}
