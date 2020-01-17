package com.gxk.jvm.classfile;

public class CodeAttribute {
  public final CodeFromByte code;
  public final int maxLocals;
  public final int maxStacks;

  public CodeAttribute(CodeFromByte code, int maxLocals, int maxStacks) {
    this.code = code;
    this.maxLocals = maxLocals;
    this.maxStacks = maxStacks;
  }
}
