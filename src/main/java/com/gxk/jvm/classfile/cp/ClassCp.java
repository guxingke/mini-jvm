package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;
import lombok.Data;

@Data
public class ClassCp extends ConstantInfo {

  public final int nameIndex;

  public ClassCp(ConstantPoolInfoEnum infoEnum, int nameIndex) {
    super(infoEnum);
    this.nameIndex = nameIndex;
  }
}
