package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.KObject;

public class Slot {
  public final Integer num;
  public final KObject ref;

  public Slot(int num) {
    this.num = num;
    this.ref = null;
  }

  public Slot(KObject ref) {
    this.num = null;
    this.ref = ref;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Slot{");
    sb.append("num=").append(num);
    sb.append(", ref=").append(ref);
    sb.append('}');
    return sb.toString();
  }
}
