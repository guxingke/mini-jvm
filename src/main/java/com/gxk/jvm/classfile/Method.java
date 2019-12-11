package com.gxk.jvm.classfile;

import com.gxk.jvm.classfile.attribute.Code;

//method_info {
//    u2             access_flags;
//    u2             name_index;
//    u2             descriptor_index;
//    u2             attributes_count;
//    attribute_info attributes[attributes_count];
//    }
public class Method {

  public final int accessFlags;
  public final String name;
  public final Descriptor descriptor;
  public final Attributes attributes;

  public Method(int accessFlags, String name, Descriptor descriptor, Attributes attributes) {
    this.accessFlags = accessFlags;
    this.name = name;
    this.descriptor = descriptor;
    this.attributes = attributes;
  }

  public Code getCode() {
    for (Attribute attribute : attributes.attributes) {
      if (attribute instanceof Code) {
        return ((Code) attribute);
      }
    }
    return null;
  }
}
