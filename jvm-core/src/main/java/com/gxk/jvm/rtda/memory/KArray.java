package com.gxk.jvm.rtda.memory;

import com.gxk.jvm.instruction.ANewArrayInst;
import com.gxk.jvm.instruction.NewArrayInst;

public class KArray extends KObject {

  public final Long[] items;
  public final Object[] primitiveItems;
  public final int size;

  private KArray(KClass clazz, Long[] items) {
    super(clazz);
    this.items = items;
    this.size = this.items.length;
    this.primitiveItems = null;
  }

  private KArray(KClass clazz, Object[] primitiveItems) {
    super(clazz);
    this.items = null;
    this.primitiveItems = primitiveItems;
    this.size = this.primitiveItems.length;
  }

  public boolean isPrimitiveArray() {
    return NewArrayInst.MAPPING.containsValue(this.clazz.name);
  }

  public static Long newArray(KClass clz, Long[] items) {
    KArray arr = new KArray(clz, items);
    Long offset = Heap.allocate(arr);
    return offset;
  }

  public static Long newArray(KClass clz, Object[] items) {
    KArray arr = new KArray(clz, items);
    Long offset = Heap.allocate(arr);
    return offset;
  }

  @Override
  public String toString() {
    return "KArray{items=" + items.length + "}@" + this.hashCode();
  }
}
