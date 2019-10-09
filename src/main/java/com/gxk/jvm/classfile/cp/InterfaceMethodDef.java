package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;
import lombok.Data;

@Data
public class InterfaceMethodDef extends ConstantInfo {

  public final int classIndex;
  public final int nameAndTypeIndex;

  public InterfaceMethodDef(ConstantPoolInfoEnum infoEnum, int classIndex, int nameAndTypeIndex) {
    super(infoEnum);
    this.classIndex = classIndex;
    this.nameAndTypeIndex = nameAndTypeIndex;
  }
}
