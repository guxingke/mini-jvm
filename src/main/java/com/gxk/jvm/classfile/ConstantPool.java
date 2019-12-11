package com.gxk.jvm.classfile;

public class ConstantPool {
  public final ConstantInfo[] infos;

  public ConstantPool(int size) {
    this.infos = new ConstantInfo[size];
  }
}
