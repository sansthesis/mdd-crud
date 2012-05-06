package com.github.jasonrose.crud.scanner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.github.jasonrose.functional.Functional;
import com.github.jasonrose.functional.FunctionalImpl;
import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

/**
 * This Mojo creates a list of all source files of a Maven project.
 * 
 * @goal generate
 * @phase generate-sources
 */
public class MDDGeneratorMojo extends AbstractMojo {

  /**
   * Location of the generated list of source files.
   * 
   * @parameter expression="${project.build.directory}/generated-sources/mdd-crud/"
   */
  private File outputDirectory;

  /**
   * Main method executed by maven for this mojo.
   * 
   * @throws MojoExecutionException propagated.
   */
  @Override
  public void execute() throws MojoExecutionException {
    final Log log = getLog();

    outputDirectory.mkdirs();
    final SourceGenerator sourceGenerator = new SourceGeneratorImpl();
    final Functional functional = new FunctionalImpl();
    final Reflections reflections = new Reflections(new ConfigurationBuilder().addUrls(ClasspathHelper.forClassLoader()).setScanners(new TypeAnnotationsScanner(), new SubTypesScanner()));
    final Set<Class<?>> entities = reflections.getTypesAnnotatedWith(Entity.class);
    log.debug("Generating output for classes: " + entities);
    final List<Emitter> emitters = Arrays.asList(new Emitter[] { new EntityDaoEmitter(sourceGenerator, functional), new EntityDefaultDaoEmitter(sourceGenerator, functional), new EntityDefaultResourceEmitter(sourceGenerator) });
    final List<Model> models = Lists.newArrayList();
    final ClassScanner scanner = new ClassScanner(new BeanAnalyzerImpl());

    // Generate an emission for entity x emitter.
    for( final Class<?> entity : entities ) {
      for( final Emitter emitter : emitters ) {
        final Model model = scanner.generateModel(entity);
        models.add(model);
        final Emission emission = emitter.emit(model);
        outputGeneratedFile(outputDirectory, emission);
      }
    }

    // Generate default Guice module with the generated service bindings defined.
    final Emission moduleEmission = new DefaultModuleEmitter(sourceGenerator).emit(models);
    outputGeneratedFile(outputDirectory, moduleEmission);
  }

  private void outputGeneratedFile(final File root, final Emission emission) throws MojoExecutionException {
    final Log log = getLog();
    final File outputFile = new File(root, emission.getFilename().replace(".", "/") + ".java");
    log.debug("Outputting to: " + outputFile);
    try {
      Files.createParentDirs(outputFile);
      outputFile.createNewFile();
      Files.write(emission.getContent(), outputFile, Charsets.UTF_8);
    } catch (IOException ioe) {
      Throwables.propagate(ioe);
    }
  }
}
