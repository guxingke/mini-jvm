package com.gxk.jvm.instruction.constants;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;

public class DConst0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushDouble(0.0d);
  }
}
