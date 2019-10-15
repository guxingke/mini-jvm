package com.gxk.jvm.rtda.heap;

import lombok.ToString;

@ToString
public class KField {
  public final String name;
  public final String descriptor;

  public Object val;

  public KField(String name, String descriptor) {
    this.name = name;
    this.descriptor = descriptor;
  }
}
