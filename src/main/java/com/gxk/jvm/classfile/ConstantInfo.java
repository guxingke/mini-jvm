package com.gxk.jvm.classfile;

import lombok.Data;

@Data
public class ConstantInfo {

  public final ConstantPoolInfoEnum infoEnum;

  public ConstantInfo(ConstantPoolInfoEnum infoEnum) {
    this.infoEnum = infoEnum;
  }
}
