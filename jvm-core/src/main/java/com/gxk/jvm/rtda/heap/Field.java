package com.gxk.jvm.rtda.heap;

import com.gxk.jvm.rtda.Slot;

public class Field {
  public final int accessFlags;
  public final String name;
  public final String descriptor;

  public Slot[] val;

  public Field(int accessFlags, String name, String descriptor) {
    this.accessFlags = accessFlags;
    this.name = name;
    this.descriptor = descriptor;
  }

  public Field(int accessFlags, String name, String descriptor, Slot[] val) {
    this.accessFlags = accessFlags;
    this.name = name;
    this.descriptor = descriptor;
    this.val = val;
  }

  public boolean isStatic() {
    return (accessFlags & 0x0008) != 0;
  }
}
