package com.gxk.jvm.classfile;

/**
 * Constant Type	Value
 * CONSTANT_Class	7
 * CONSTANT_Fieldref	9
 * CONSTANT_Methodref	10
 * CONSTANT_InterfaceMethodref	11
 * CONSTANT_String	8
 * CONSTANT_Integer	3
 * CONSTANT_Float	4
 * CONSTANT_Long	5
 * CONSTANT_Double	6
 * CONSTANT_NameAndType	12
 * CONSTANT_Utf8	1
 * CONSTANT_MethodHandle	15
 * CONSTANT_MethodType	16
 * CONSTANT_InvokeDynamic	18
 */
public enum ConstantPoolInfoEnum {
  CONSTANT_Class(7),
  CONSTANT_Fieldref(9),
  CONSTANT_Methodref(10),
  CONSTANT_InterfaceMethodref(11),
  CONSTANT_String(8),
  CONSTANT_Integer(3),
  CONSTANT_Float(4),
  CONSTANT_Long(5),
  CONSTANT_Double(6),
  CONSTANT_NameAndType(12),
  CONSTANT_Utf8(1),
  CONSTANT_MethodHandle(15),
  CONSTANT_MethodType(16),
  CONSTANT_InvokeDynamic(18),
  ;

  int value;

  ConstantPoolInfoEnum(int value) {
    this.value = value;
  }


  public static ConstantPoolInfoEnum of(int value) {
    for (ConstantPoolInfoEnum constantPoolInfoEnum : ConstantPoolInfoEnum.values()) {
      if (constantPoolInfoEnum.value == value) {
        return constantPoolInfoEnum;
      }
    }
    return null;
  }
}
