package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DupInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    // TODO ...
    Object obj = frame.operandStack.popRef();
    frame.operandStack.pushRef(obj);
    frame.operandStack.pushRef(obj);
  }
}
