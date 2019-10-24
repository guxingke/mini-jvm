package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DupX1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object v2= frame.operandStack.popRef();
    Object v1= frame.operandStack.popRef();
    frame.operandStack.pushRef(v1);
    frame.operandStack.pushRef(v2);
    frame.operandStack.pushRef(v1);
  }
}
