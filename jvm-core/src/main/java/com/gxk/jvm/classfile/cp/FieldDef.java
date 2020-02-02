package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;

public class FieldDef extends ConstantInfo {

  public final int classIndex;
  public final int nameAndTypeIndex;

  public FieldDef(int infoEnum, int classIndex, int nameAndTypeIndex) {
    super(infoEnum);
    this.classIndex = classIndex;
    this.nameAndTypeIndex = nameAndTypeIndex;
  }
}
