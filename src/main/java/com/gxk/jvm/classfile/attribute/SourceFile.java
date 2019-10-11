package com.gxk.jvm.classfile.attribute;

import com.gxk.jvm.classfile.Attribute;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SourceFile extends Attribute {

  public final String name;

  //SourceFile_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 sourcefile_index;
//  }
  public SourceFile(String name) {
    this.name = name;
  }
}
