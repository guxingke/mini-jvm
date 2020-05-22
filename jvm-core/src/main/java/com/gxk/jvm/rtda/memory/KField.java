package com.gxk.jvm.rtda.memory;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.util.Utils;

public class KField {

  public final int accessFlags;
  public final String name;
  public final String descriptor;

  private Slot[] val;

  public KField(int accessFlags, String name, String descriptor) {
    this.accessFlags = accessFlags;
    this.name = name;
    this.descriptor = descriptor;
  }

  public KField(int accessFlags, String name, String descriptor, Slot[] val) {
    this.accessFlags = accessFlags;
    this.name = name;
    this.descriptor = descriptor;
    this.val = val;
  }

  public boolean isStatic() {
    return (accessFlags & 0x0008) != 0;
  }

  public void incRefCnt() {
    Utils.incRefCnt(this.val);
  }

  public Slot[] getVal() {
    return this.val;
  }

  public void setVal(Slot[] val) {
    this.val = val;
  }

  public void decRefCnt() {
    Utils.decRefCnt(this.val);
  }
}
