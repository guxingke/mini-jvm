package com.gxk.jvm.classfile;

public class CodeAttribute {
  public final Code code;
  public final int maxLocals;
  public final int maxStacks;

  public CodeAttribute(Code code, int maxLocals, int maxStacks) {
    this.code = code;
    this.maxLocals = maxLocals;
    this.maxStacks = maxStacks;
  }
}
