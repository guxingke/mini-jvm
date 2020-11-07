package com.gxk.jvm.classfile;

public class Methods {

  public final MethodInfo[] methodInfos;
  public Methods(int methodCount) {
    this.methodInfos = new MethodInfo[methodCount];
  }
}
