package com.gxk.jvm.classfile;

import com.gxk.jvm.instruction.Instruction;

import java.util.Map;

public class Code {

  private final Map<Integer, Instruction> instructions;

  public Code(Map<Integer, Instruction> instructions) {
    this.instructions = instructions;
  }

  public Instruction getInst(int pc) {
    return this.instructions.get(pc);
  }
}
