package com.github.jasonrose.crud.scanner;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.DirectoryScanner;

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
   * Project the plugin is called from.
   * 
   * @parameter expression="${project}"
   */
  private MavenProject project;

  /**
   * Defines files in the source directories to include (all .java files by default).
   * 
   * @parameter
   */
  private final String[] includes = { "**/*.java" };

  /**
   * Defines which of the included files in the source directories to exclude (non by default).
   * 
   * @parameter
   */
  private String[] excludes;

  /**
   * Main method executed by maven for this mojo.
   * 
   * @throws MojoExecutionException propagated.
   */
  @SuppressWarnings("unchecked")
  @Override
  public void execute() throws MojoExecutionException {
    final Log log = getLog();

    log.info("creating source list file '" + outputDirectory.getAbsolutePath() + "'");

    outputDirectory.mkdirs();
    scan(project.getCompileSourceRoots(), outputDirectory);
  }

  /**
   * Scans a set of directories.
   * 
   * @param roots Directories to scan
   * @throws MojoExecutionException propagated.
   */
  private void scan(final List<String> roots, final File directory) throws MojoExecutionException {
    for( final String root : roots ) {
      scan(new File(root), directory);
    }
  }

  /**
   * Scans a single directory.
   * 
   * @param root Directory to scan
   * @throws MojoExecutionException in case of IO errors
   */
  private void scan(final File root, final File directory) throws MojoExecutionException {
    final Log log = getLog();

    if( !root.exists() ) {
      return;
    }

    log.info("scanning source file directory '" + root + "'");

    final DirectoryScanner directoryScanner = new DirectoryScanner();
    directoryScanner.setIncludes(includes);
    directoryScanner.setExcludes(excludes);
    directoryScanner.setBasedir(root);
    directoryScanner.scan();

    for( final String fileName : directoryScanner.getIncludedFiles() ) {
      final File file = new File(root, fileName);
      try {
        log.info("I'd be evaluating " + file.getName() + " if I knew how.");
      } catch( final Exception e ) {
        throw new MojoExecutionException("io error while writing source list", e);
      }
    }
  }
}