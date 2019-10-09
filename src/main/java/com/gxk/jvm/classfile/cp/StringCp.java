package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;
import lombok.Data;

@Data
public class StringCp extends ConstantInfo {

  public final int stringIndex;

  public StringCp(ConstantPoolInfoEnum infoEnum, int stringIndex) {
    super(infoEnum);
    this.stringIndex = stringIndex;
  }
}
