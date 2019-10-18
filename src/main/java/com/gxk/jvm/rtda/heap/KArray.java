package com.gxk.jvm.rtda.heap;

public class KArray {
  public final KClass clazz;
  public final Object[] items;

  public KArray(KClass clazz, Object[] items) {
    this.clazz = clazz;
    this.items = items;
  }
}
