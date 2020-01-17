package com.gxk.jvm.classfile;

public final class ExceptionTable {

  public final Exception[] exceptions;

  public ExceptionTable(Exception[] exceptions) {
    this.exceptions = exceptions;
  }
}
