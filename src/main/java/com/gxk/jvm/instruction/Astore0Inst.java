package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Astore0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.operandStack.popRef();
    frame.localVars.setRef(0, tmp);
  }
}
