package com.github.jasonrose.crud.scanner;

public class Emission {
  private String content;
  private String filename;

  public Emission(final String filename, final String content) {
    this.filename = filename;
    this.content = content;
  }

  public String getContent() {
    return this.content;
  }

  public String getFilename() {
    return this.filename;
  }

  public void setContent(final String content) {
    this.content = content;
  }

  public void setFilename(final String filename) {
    this.filename = filename;
  }
}
