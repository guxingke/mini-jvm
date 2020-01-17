package com.gxk.jvm.classfile;

//field_info {
//    u2             access_flags;
//    u2             name_index;
//    u2             descriptor_index;
//    u2             attributes_count;
//    attribute_info attributes[attributes_count];
//    }
public class Field {

  public final int accessFlags;
  public final String name;
  public final Descriptor descriptor;
  public final Attributes attributes;

  public Field(int accessFlags, String name, Descriptor descriptor, Attributes attributes) {
    this.accessFlags = accessFlags;
    this.name = name;
    this.descriptor = descriptor;
    this.attributes = attributes;
  }
}
