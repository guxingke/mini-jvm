package com.gxk.jvm.classfile.attribute;

import com.gxk.jvm.classfile.Attribute;

public class ConstantValue extends Attribute {

  public final Object val;

  //  onstantValue_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 constantvalue_index;
//  }
  public ConstantValue(Object val) {
    this.val = val;
  }
}
