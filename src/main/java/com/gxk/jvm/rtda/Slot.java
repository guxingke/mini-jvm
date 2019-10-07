package com.gxk.jvm.rtda;

public class Slot {
  public final Integer num;
  public final Object ref;

  public Slot(Integer num) {
    this.num = num;
    this.ref = null;
  }

  public Slot(Object ref) {
    this.num = null;
    this.ref = ref;
  }
}
