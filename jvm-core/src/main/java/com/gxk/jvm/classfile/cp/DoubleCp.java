package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;

public class DoubleCp extends ConstantInfo {

  public final double val;

  public DoubleCp(int infoEnum, double val) {
    super(infoEnum);
    this.val = val;
  }
}