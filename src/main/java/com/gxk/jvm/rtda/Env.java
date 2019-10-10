package com.gxk.jvm.rtda;

import com.gxk.jvm.classfile.ConstantPool;

public final class Env {
  public final ConstantPool constantPool;

  public Env(ConstantPool constantPool) {
    this.constantPool = constantPool;
  }
}
