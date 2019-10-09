package com.gxk.jvm.classfile;

import lombok.Data;

@Data
public class Methods {

  public final Method[] methods;
  public Methods(int methodCount) {
    this.methods = new Method[methodCount];
  }
}
