package com.github.jasonrose.crud.example.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.validation.spi.ValidationProvider;

import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jasonrose.crud.om.generated.DefaultModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceServletConfig extends GuiceServletContextListener {
  private final Logger log = LoggerFactory.getLogger(getClass());

  private ServletContext servletContext;

  @Override
  public void contextInitialized(final ServletContextEvent servletContextEvent) {
    servletContext = servletContextEvent.getServletContext();
    super.contextInitialized(servletContextEvent);
  }

  @Override
  protected Injector getInjector() {
    log.info("getInjector called");
    final Injector injector = Guice.createInjector(new JerseyServletModule() {
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
    }, new DefaultModule());

    return injector;
  }
}
