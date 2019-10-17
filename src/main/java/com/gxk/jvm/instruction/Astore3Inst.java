package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Astore3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.operandStack.popRef();
    frame.localVars.setRef(3, tmp);
  }
}
