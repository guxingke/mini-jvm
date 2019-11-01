package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;
import lombok.ToString;

@ToString
public class MethodDef extends ConstantInfo {

  public final int classIndex;
  public final int nameAndTypeIndex;

  public MethodDef(ConstantPoolInfoEnum infoEnum, int classIndex, int nameAndTypeIndex) {
    super(infoEnum);
    this.classIndex = classIndex;
    this.nameAndTypeIndex = nameAndTypeIndex;
  }
}
