package com.github.jasonrose.crud.om;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class OneToManyEntity extends AbstractEntity {

  public OneToManyEntity() {
    setManyToOnes(new HashSet<ManyToOneEntity>());
  }

  @NotNull
  @Size(min = 1, max = 255)
  public String getSomething() {
    return getAndCoerce("something");
  }

  public void setSomething(final String value) {
    put("something", value);
  }

  @OneToMany
  @NotNull
  public Set<ManyToOneEntity> getManyToOnes() {
    return getAndCoerce("manyToOnes");
  }

  public void setManyToOnes(final Set<ManyToOneEntity> value) {
    put("manyToOnes", value);
  }

}
