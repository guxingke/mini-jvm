package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class SALoadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    int val = ((Short) array.items[index]).intValue();
    frame.pushInt(val);
  }
}