package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;

public class FloatCp extends ConstantInfo {

  public final float val;

  public FloatCp(int infoEnum, float val) {
    super(infoEnum);
    this.val = val;
  }
}