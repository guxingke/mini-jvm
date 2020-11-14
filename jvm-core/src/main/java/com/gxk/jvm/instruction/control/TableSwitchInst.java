package com.gxk.jvm.instruction.control;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

import java.util.Map;

public class TableSwitchInst implements Instruction {
  public final int offset;
  public final int def;
  public final int low;
  public final int high;
  public final Map<Integer, Integer> table;

  public TableSwitchInst(int offset, int def, int low, int high, Map<Integer,Integer> table) {
    this.offset = offset;
    this.def = def;
    this.low = low;
    this.high = high;
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