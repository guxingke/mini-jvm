package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.KObject;

public class Slot {

  // 基本类型
  public int val;
  // 引用类型
  public KObject ref;

  private Slot(int val, KObject ref) {
    this.val = val;
    this.ref = ref;
  }

  public static Slot val(int val) {
    return new Slot(val, null);
  }

  public static Slot ref(KObject ref) {
    return new Slot(0, ref);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Slot{");
    sb.append("val=").append(val);
    sb.append(", ref=").append(ref);
    sb.append('}');
    return sb.toString();
  }
}
