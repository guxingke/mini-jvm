package com.gxk.jvm.rtda.heap;

public class InstanceArray extends ArrayInstance {

  public final Object[] items;

  public InstanceArray(Class clazz, Object[] items) {
    super(clazz, items.length);
    this.items = items;
  }

  @Override
  public String toString() {
    return "KArray{items=" + items.length + "}@" + this.hashCode();
  }
}
