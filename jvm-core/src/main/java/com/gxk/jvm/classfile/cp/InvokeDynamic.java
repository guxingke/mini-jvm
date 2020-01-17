package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;

public class InvokeDynamic extends ConstantInfo {

  public final int bootstrapMethodAttrIndex;
  public final int nameAndTypeIndex;

  public InvokeDynamic(ConstantPoolInfoEnum infoEnum, int bootstrapMethodAttrIndex, int nameAndTypeIndex) {
    super(infoEnum);
    this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
    this.nameAndTypeIndex = nameAndTypeIndex;
  }
}
