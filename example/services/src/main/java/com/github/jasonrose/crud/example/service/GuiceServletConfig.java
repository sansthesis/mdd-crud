package com.github.jasonrose.crud.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.validation.spi.ValidationProvider;

import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jasonrose.crud.om.Contact;
import com.github.jasonrose.crud.om.generated.GeneratedModule;
import com.github.jasonrose.crud.security.Authorizer;
import com.github.jasonrose.crud.security.spi.NoOpAuthorizerImpl;
import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.util.Modules;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceServletConfig extends GuiceServletContextListener {
  private final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public void contextInitialized(final ServletContextEvent servletContextEvent) {
    super.contextInitialized(servletContextEvent);
  }

  @Override
  protected Injector getInjector() {
    log.info("getInjector called");
    
    final List<? extends Module> defaultModules = ImmutableList.of(new JerseyServletModule() {
      @Override
      protected void configureServlets() {
        install(new JpaPersistModule("mdd-crud-example"));

        bind(ValidationProvider.class).to(HibernateValidator.class);

        final Map<String, String> params = new HashMap<String, String>();
        params.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
        params.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.github.jasonrose.crud.om.generated");
        serve("/mdd-test/*").with(GuiceContainer.class, params);

        filter("/*").through(PersistFilter.class);
      }
    }, new GeneratedModule());
    
    final Module override = new AbstractModule() {
      @Override
      protected void configure() {
        bind(new TypeLiteral<Authorizer<Contact>>() {}).toInstance(new NoOpAuthorizerImpl<Contact>() {
          @Override
          public void authorize(com.github.jasonrose.crud.security.Authorizer.Operation operation, Contact entity) {
            log.error("nope");
            super.authorize(operation, entity);
          }
          
          @Override
          public void authorize(com.github.jasonrose.crud.security.Authorizer.Operation operation) {
            log.error("nope");
            super.authorize(operation);
          }
          
          @Override
          public void authorize(com.github.jasonrose.crud.security.Authorizer.Operation operation, long id) {
            log.error("nope");
            super.authorize(operation, id);
          }
        });
      }
    };
    
    return Guice.createInjector(Modules.override(defaultModules).with(override));
  }
}
