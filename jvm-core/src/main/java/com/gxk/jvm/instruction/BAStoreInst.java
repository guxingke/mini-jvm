package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class BAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    byte val = frame.popInt().byteValue();
    Integer index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    array.items[index] = val;
  }
}