package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;

public class MethodType extends ConstantInfo {

  public final int descriptorIndex;

  public MethodType(ConstantPoolInfoEnum infoEnum, int descriptorIndex) {
    super(infoEnum);
    this.descriptorIndex = descriptorIndex;
  }
}
