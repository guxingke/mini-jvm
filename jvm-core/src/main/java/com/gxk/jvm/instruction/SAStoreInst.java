package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class SAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    short val = (short) frame.popInt();
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    array.items[index] = val;
  }
}