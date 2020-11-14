package com.gxk.jvm.instruction.conversions;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;

public class I2sInst implements Instruction {

  @Override
  public int offset() {
    return 1;
  }

  @Override
  public void execute(Frame frame) {
    Integer integer = frame.popInt();
    short val = integer.shortValue();
    frame.pushInt(((int) val));
  }
}