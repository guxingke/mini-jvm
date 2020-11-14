package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class CAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    char val = (char) frame.popInt();
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    array.items[index] = val;
  }

  @Override
  public String format() {
    return "castore";
  }
}