package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;

public class ClassCp extends ConstantInfo {

  public final int nameIndex;

  public ClassCp(int infoEnum, int nameIndex) {
    super(infoEnum);
    this.nameIndex = nameIndex;
  }
}
