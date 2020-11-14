package com.gxk.jvm.instruction.loads;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;

public class ILoadNInst implements Instruction {
  public final int n;

  public ILoadNInst(int n) {
    this.n = n;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.getInt(n);
    frame.pushInt(tmp);
  }

  @Override
  public String format() {
    return "iload " + n;
  }
}
