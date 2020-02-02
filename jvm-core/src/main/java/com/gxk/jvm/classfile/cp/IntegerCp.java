package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;

public class IntegerCp extends ConstantInfo {

  public final int val;

  public IntegerCp(int infoEnum, int val) {
    super(infoEnum);
    this.val = val;
  }
}
