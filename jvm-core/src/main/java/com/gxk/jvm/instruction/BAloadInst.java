package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.KArray;

public class BAloadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    if (array.items instanceof Boolean[]) {
      frame.pushInt(((Boolean) array.items[index]) ? 1 : 0);
      return;
    }
    byte item = (byte) array.items[index];
    frame.pushInt((int) item);
  }
}