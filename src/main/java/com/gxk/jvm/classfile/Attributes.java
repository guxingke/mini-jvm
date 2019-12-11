package com.gxk.jvm.classfile;

public class Attributes {

  public final Attribute[] attributes;

  public Attributes(int size) {
    this.attributes = new Attribute[size];
  }
}
