package com.github.jasonrose.crud.om;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.praxissoftware.rest.core.AbstractMapEntity;

@MappedSuperclass
public abstract class AbstractEntity extends AbstractMapEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return getAndCoerce("id");
  }

  public void setId(final Long id) {
    put("id", id);
  }
}
