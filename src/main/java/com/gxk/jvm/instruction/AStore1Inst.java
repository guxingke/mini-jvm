package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class AStore1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.operandStack.popRef();
    frame.localVars.setRef(1, tmp);
  }
}
