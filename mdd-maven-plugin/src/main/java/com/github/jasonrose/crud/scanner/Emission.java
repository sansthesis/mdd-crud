package com.github.jasonrose.crud.scanner;

/**
 * An emission is a generated source file.
 * @author Jason Rose
 * 
 */
public class Emission {
  private final String content;
  private final String filename;

  public Emission(final String filename, final String content) {
    this.filename = filename;
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public String getFilename() {
    return filename;
  }
}
