package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantPoolInfoEnum;
import lombok.Data;

@Data
public class InterfaceMethodDef extends MethodDef{

  public InterfaceMethodDef(ConstantPoolInfoEnum infoEnum, int classIndex, int nameAndTypeIndex) {
    super(infoEnum, classIndex, nameAndTypeIndex);
  }
}
