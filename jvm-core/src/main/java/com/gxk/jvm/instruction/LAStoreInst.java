package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class LAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    long val = frame.popLong();
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    ((long[]) array.items)[index] = val;
  }

  @Override
  public String format() {
    return "lastore";
  }
}