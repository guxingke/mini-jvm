package com.gxk.jvm.classfile;

import lombok.Data;

@Data
public class ConstantPool {
  public final ConstantInfo[] infos;

  public ConstantPool(int size) {
    this.infos = new ConstantInfo[size];
  }
}
