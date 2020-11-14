package com.gxk.jvm.rtda.heap;

public class ArrayInstance extends Instance {

  public final int len;

  public ArrayInstance(Class clazz, int len) {
    super(clazz);
    this.len = len;
  }
}
