package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;

public class DupX1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Slot s1 = frame.operandStack.popSlot();
    Slot s2 = frame.operandStack.popSlot();
    frame.operandStack.pushSlot(s1);
    frame.operandStack.pushSlot(s2);
    frame.operandStack.pushSlot(s1);
  }
}