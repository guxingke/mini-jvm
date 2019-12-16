package com.gxk.jvm.classfile.attribute;

import com.gxk.jvm.classfile.Attribute;
import com.gxk.jvm.classfile.Attributes;
import com.gxk.jvm.classfile.ExceptionTable;
import com.gxk.jvm.instruction.Instruction;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Code extends Attribute {

  //  Code_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 max_stack;
//    u2 max_locals;
//    u4 code_length;
//    u1 code[code_length];
//    u2 exception_table_length;
//    {   u2 start_pc;
//      u2 end_pc;
//      u2 handler_pc;
//      u2 catch_type;
//    } exception_table[exception_table_length];
//    u2 attributes_count;
//    attribute_info attributes[attributes_count];
//  }
  public final int maxStacks;
  public final int maxLocals;
  public final Instruction[] instructions;
  public final ExceptionTable exceptionTable;
  public final Attributes attributes;

  public Code(int maxStacks, int maxLocals, Instruction[] instructions,
      ExceptionTable exceptionTable, Attributes attributes) {
    this.maxStacks = maxStacks;
    this.maxLocals = maxLocals;
    this.instructions = instructions;
    this.exceptionTable = exceptionTable;
    this.attributes = attributes;
  }

  public Map<Integer, Instruction> getInstructions() {
    Map<Integer, Instruction> map = new LinkedHashMap<>(instructions.length);
    int pc = 0;
    for (Instruction instruction : instructions) {
      map.put(pc, instruction);
      pc += instruction.offset();
    }
    return map;
  }
}
