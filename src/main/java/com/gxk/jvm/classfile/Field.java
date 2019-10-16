package com.gxk.jvm.classfile;

import com.gxk.jvm.classfile.attribute.Code;
import lombok.Data;

//method_info {
//    u2             access_flags;
//    u2             name_index;
//    u2             descriptor_index;
//    u2             attributes_count;
//    attribute_info attributes[attributes_count];
//    }
@Data
public class Field {

  public final int accessFlags;
  public final String name;
  public final Descriptor descriptor;
  public final Attributes attributes;
}
