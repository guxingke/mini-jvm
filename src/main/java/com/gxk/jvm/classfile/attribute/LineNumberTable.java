package com.gxk.jvm.classfile.attribute;

import com.gxk.jvm.classfile.Attribute;

public class LineNumberTable extends Attribute {

  //  LineNumberTable_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 line_number_table_length;
//    {   u2 start_pc;
//      u2 line_number;
//    } line_number_table[line_number_table_length];
//  }
  public final Line[] lines;

  public LineNumberTable(Line[] lines) {
    this.lines = lines;
  }

  public static class Line {

    public final int startPc;
    public final int lineNumber;

    public Line(int startPc, int lineNumber) {
      this.startPc = startPc;
      this.lineNumber = lineNumber;
    }
  }
}
