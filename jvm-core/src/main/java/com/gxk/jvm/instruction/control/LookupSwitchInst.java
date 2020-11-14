package com.gxk.jvm.instruction.control;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

import java.util.Map;

public class LookupSwitchInst implements Instruction {
  public final int offset;
  public final int def;
  public final int pairsCnt;
  public final Map<Integer, Integer> table;

  public LookupSwitchInst(int offset, int def, int pairsCnt, Map<Integer, Integer> table) {
    this.offset = offset;
    this.def = def;
    this.pairsCnt = pairsCnt;
    this.table = table;
  }

  @Override
  public int offset() {
    return this.offset;
  }

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.popInt();
    Integer jump = table.getOrDefault(tmp, def);
    frame.nextPc = frame.getPc() + jump;
  }
}