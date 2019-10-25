package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;
import lombok.Data;

@Data
public class DoubleCp extends ConstantInfo {

  public final double val;

  public DoubleCp(ConstantPoolInfoEnum infoEnum, double val) {
    super(infoEnum);
    this.val= val;
  }
}