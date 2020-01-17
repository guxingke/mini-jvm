package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;

public class FloatCp extends ConstantInfo {

  public final float val;

  public FloatCp(ConstantPoolInfoEnum infoEnum, float val) {
    super(infoEnum);
    this.val= val;
  }
}