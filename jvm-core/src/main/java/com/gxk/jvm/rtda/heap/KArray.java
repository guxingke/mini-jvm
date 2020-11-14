package com.gxk.jvm.rtda.heap;

public class KArray extends Instance {
  public final Object[] items;

  public KArray(Class clazz, Object[] items) {
    super(clazz);
    this.items = items;
  }

  @Override
  public String toString() {
    return "KArray{items=" + items.length + "}@" + this.hashCode();
  }
}
