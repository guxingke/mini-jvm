package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;

public class NameAndType extends ConstantInfo {

  public final int nameIndex;
  public final int descriptionIndex;

  public NameAndType(ConstantPoolInfoEnum infoEnum, int nameIndex, int descriptionIndex) {
    super(infoEnum);
    this.nameIndex = nameIndex;
    this.descriptionIndex = descriptionIndex;
  }
}
