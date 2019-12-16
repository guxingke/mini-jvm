package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;

public class DupInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Slot slot = frame.popSlot();
    switch (slot.type) {
      case Slot.LONG_LOW:
        frame.pushSlot(slot);
        Long val = frame.popLong();
        frame.pushLong(val);
        frame.pushLong(val);
        break;
      case Slot.DOUBLE_LOW:
        frame.pushSlot(slot);
        Double dval = frame.popDouble();
        frame.pushDouble(dval);
        frame.pushDouble(dval);
        break;
      default:
        frame.pushSlot(slot);
        frame.pushSlot(slot);
    }
  }

  @Override
  public String format() {
    return "dup";
  }
}
