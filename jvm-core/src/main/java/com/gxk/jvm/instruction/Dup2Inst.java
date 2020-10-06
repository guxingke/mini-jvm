package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;

public class Dup2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Slot s1 = frame.popSlot();
    Slot s2 = frame.popSlot();
    frame.pushSlot(s2);
    frame.pushSlot(s1);
    frame.pushSlot(s2);
    frame.pushSlot(s1);
  }
}
