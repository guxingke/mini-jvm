package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DLoad3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double tmp = frame.localVars.getDouble(3);
    frame.operandStack.pushDouble(tmp);
  }
}
