package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.Instance;

public class Slot {
  public Integer num;
  public Instance ref;

  public Slot(int num) {
    this.num = num;
    this.ref = null;
  }

  public Slot(Instance ref) {
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
