package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;

public class StringCp extends ConstantInfo {

  public final int stringIndex;

  public StringCp(int infoEnum, int stringIndex) {
    super(infoEnum);
    this.stringIndex = stringIndex;
  }
}
