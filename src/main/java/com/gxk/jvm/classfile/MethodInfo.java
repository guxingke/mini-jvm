package com.gxk.jvm.classfile;


public class MethodInfo {
  public final CodeAttribute code;

  public MethodInfo(CodeAttribute codeAttribute) {
    this.code = codeAttribute;
  }
}
