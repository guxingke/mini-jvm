package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;

public class MethodType extends ConstantInfo {

  public final int descriptorIndex;

  public MethodType(int infoEnum, int descriptorIndex) {
    super(infoEnum);
    this.descriptorIndex = descriptorIndex;
  }
}
