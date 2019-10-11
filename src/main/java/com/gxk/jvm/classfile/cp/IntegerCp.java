package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;
import lombok.Data;

@Data
public class IntegerCp extends ConstantInfo {

  public final int val;

  public IntegerCp(ConstantPoolInfoEnum infoEnum, int val) {
    super(infoEnum);
    this.val= val;
  }
}
