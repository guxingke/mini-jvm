package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantPoolInfoEnum;

public class InterfaceMethodDef extends MethodDef{

  public InterfaceMethodDef(ConstantPoolInfoEnum infoEnum, int classIndex, int nameAndTypeIndex) {
    super(infoEnum, classIndex, nameAndTypeIndex);
  }
}
