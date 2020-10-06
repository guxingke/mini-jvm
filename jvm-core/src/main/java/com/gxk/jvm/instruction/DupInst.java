package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;

public class DupInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Slot slot = frame.popSlot();
    frame.pushSlot(slot);
    frame.pushSlot(slot);
  }

  @Override
  public String format() {
    return "dup";
  }
}
