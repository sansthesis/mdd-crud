package com.github.jasonrose.crud.om.generated;

public class GeneratedModule extends com.google.inject.AbstractModule {
  @Override
  protected void configure() {
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Dao<com.github.jasonrose.crud.om.Contact>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedContactDao.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Service<com.github.jasonrose.crud.om.Contact>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedContactService.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.validation.Validator<com.github.jasonrose.crud.om.Contact>>() {}).to(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.validation.spi.NoOpValidatorImpl<com.github.jasonrose.crud.om.Contact>>() {});
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.security.Authorizer<com.github.jasonrose.crud.om.Contact>>() {}).to(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.security.spi.NoOpAuthorizerImpl<com.github.jasonrose.crud.om.Contact>>() {});
    bind(com.github.jasonrose.crud.om.generated.GeneratedContactResource.class).to(com.github.jasonrose.crud.om.generated.GeneratedContactResourceImpl.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Dao<com.github.jasonrose.crud.om.Person>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedPersonDao.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Service<com.github.jasonrose.crud.om.Person>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedPersonService.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.validation.Validator<com.github.jasonrose.crud.om.Person>>() {}).to(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.validation.spi.NoOpValidatorImpl<com.github.jasonrose.crud.om.Person>>() {});
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.security.Authorizer<com.github.jasonrose.crud.om.Person>>() {}).to(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.security.spi.NoOpAuthorizerImpl<com.github.jasonrose.crud.om.Person>>() {});
    bind(com.github.jasonrose.crud.om.generated.GeneratedPersonResource.class).to(com.github.jasonrose.crud.om.generated.GeneratedPersonResourceImpl.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Dao<com.github.jasonrose.crud.om.Division>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedDivisionDao.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.om.Service<com.github.jasonrose.crud.om.Division>>() {}).to(com.github.jasonrose.crud.om.generated.GeneratedDivisionService.class);
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.validation.Validator<com.github.jasonrose.crud.om.Division>>() {}).to(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.validation.spi.NoOpValidatorImpl<com.github.jasonrose.crud.om.Division>>() {});
    bind(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.security.Authorizer<com.github.jasonrose.crud.om.Division>>() {}).to(new com.google.inject.TypeLiteral<com.github.jasonrose.crud.security.spi.NoOpAuthorizerImpl<com.github.jasonrose.crud.om.Division>>() {});
    bind(com.github.jasonrose.crud.om.generated.GeneratedDivisionResource.class).to(com.github.jasonrose.crud.om.generated.GeneratedDivisionResourceImpl.class);
  }
}
