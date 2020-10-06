package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class FAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float val = frame.popFloat();
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    ((float[]) array.items)[index] = val;
  }
}