package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class IAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int val = frame.popInt();
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    ((int[]) array.items)[index] = val;
  }

  @Override
  public String format() {
    return "iastore";
  }
}