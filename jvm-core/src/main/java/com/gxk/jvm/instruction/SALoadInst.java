package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class SALoadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    int val = ((short[]) array.items)[index];
    frame.pushInt(val);
  }
}