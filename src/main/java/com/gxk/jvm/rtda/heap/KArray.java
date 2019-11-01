package com.gxk.jvm.rtda.heap;

public class KArray extends KObject{
  public final Object[] items;

  public KArray(KClass clazz, Object[] items) {
    super(clazz);
    this.items = items;
  }

  @Override
  public String toString() {
    return "KArray{" +
        "items=" + items.length +
        '}';
  }
}
