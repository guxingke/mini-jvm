package com.gxk.jvm.rtda.heap;

public class KArray extends KObject {
  public final Object items;
  public final int length;

  public KArray(KClass clazz, Object items, int length) {
    super(clazz);
    this.items = items;
    this.length = length;
  }

  @Override
  public String toString() {
    return "KArray{items=" + length + "}@" + this.hashCode();
  }
}
