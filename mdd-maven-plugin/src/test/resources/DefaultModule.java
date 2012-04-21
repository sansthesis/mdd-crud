package com.github.jasonrose.crud.om.generated;

import com.github.jasonrose.crud.om.generated.ContactDao;
import com.github.jasonrose.crud.om.generated.DivisionDefaultDao;
import com.github.jasonrose.crud.om.generated.DivisionDao;
import com.github.jasonrose.crud.om.generated.ContactDefaultDao;
import com.github.jasonrose.crud.om.generated.PersonDao;
import com.github.jasonrose.crud.om.generated.PersonDefaultDao;
import com.google.inject.AbstractModule;

public class DefaultModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PersonDao.class).to(PersonDefaultDao.class);
    bind(ContactDao.class).to(ContactDefaultDao.class);
    bind(DivisionDao.class).to(DivisionDefaultDao.class);
  }
}
