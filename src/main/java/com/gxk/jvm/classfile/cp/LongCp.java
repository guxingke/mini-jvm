package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;
import lombok.Data;

@Data
public class LongCp extends ConstantInfo {

  public final long val;

  public LongCp(ConstantPoolInfoEnum infoEnum, long val) {
    super(infoEnum);
    this.val= val;
  }
}