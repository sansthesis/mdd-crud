package com.github.jasonrose.crud.om.generated;

public class GeneratedModule extends com.google.inject.AbstractModule {
  @Override
  protected void configure() {
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Dao<com.github.jasonrose.crud.om.Contact>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedContactDao.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Service<com.github.jasonrose.crud.om.Contact>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedContactService.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Dao<com.github.jasonrose.crud.om.Person>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedPersonDao.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Service<com.github.jasonrose.crud.om.Person>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedPersonService.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Dao<com.github.jasonrose.crud.om.Division>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedDivisionDao.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Service<com.github.jasonrose.crud.om.Division>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedDivisionService.class);
  }
}
