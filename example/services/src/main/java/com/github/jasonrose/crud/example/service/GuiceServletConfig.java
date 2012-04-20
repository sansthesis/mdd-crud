package com.github.jasonrose.crud.example.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jasonrose.crud.om.generated.ContactDao;
import com.github.jasonrose.crud.om.generated.ContactDefaultDao;
import com.github.jasonrose.crud.om.generated.DivisionDao;
import com.github.jasonrose.crud.om.generated.DivisionDefaultDao;
import com.github.jasonrose.crud.om.generated.PersonDao;
import com.github.jasonrose.crud.om.generated.PersonDefaultDao;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
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
        final Map<String, String> params = new HashMap<String, String>();
        params.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
        params.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.github.jasonrose.crud.om.generated");
        serve("/mdd-test/*").with(GuiceContainer.class, params);
      }
    }, new AbstractModule() {
      @Override
      protected void configure() {
        bind(ContactDao.class).to(ContactDefaultDao.class);
        bind(DivisionDao.class).to(DivisionDefaultDao.class);
        bind(PersonDao.class).to(PersonDefaultDao.class);
      }
    });

    return injector;
  }
}