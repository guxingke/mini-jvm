package com.gxk.jvm.classfile;

import lombok.Data;

@Data
public class Attributes {

  public final Attribute[] attributes;

  public Attributes(int size) {
    this.attributes = new Attribute[size];
  }
}
