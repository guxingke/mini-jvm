package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class BAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int val = frame.popInt();
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    if (array.items instanceof boolean[]) {
      ((boolean[]) array.items)[index] = val != 0;
    } else {
      ((byte[]) array.items)[index] = (byte) val;
    }
  }
}