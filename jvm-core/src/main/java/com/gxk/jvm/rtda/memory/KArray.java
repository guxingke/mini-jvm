package com.gxk.jvm.rtda.memory;

public class KArray extends KObject {

  public final Long[] items;
  public final int size;

  public KArray(KClass clazz, Long[] items) {
    super(clazz);
    this.items = items;
    this.size = this.items.length;
  }

  public static Long newArray(KClass clz, Long[] items) {
    KArray arr = new KArray(clz, items);
    Long offset = Heap.allocate(arr);
    return offset;
  }

  @Override
  public String toString() {
    return "KArray{items=" + items.length + "}@" + this.hashCode();
  }
}
