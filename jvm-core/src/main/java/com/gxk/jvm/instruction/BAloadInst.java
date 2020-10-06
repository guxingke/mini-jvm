package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class BAloadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    if (array.items instanceof boolean[]) {
      frame.pushInt((((boolean[]) array.items)[index]) ? 1 : 0);
      return;
    }
    byte item = ((byte[]) array.items)[index];
    frame.pushInt(item);
  }
}