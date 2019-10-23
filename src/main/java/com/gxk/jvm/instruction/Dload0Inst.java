package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Dload0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double tmp = frame.localVars.getDouble(0);
    frame.operandStack.pushDouble(tmp);
  }
}
