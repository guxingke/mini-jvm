package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class CAloadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    char item = ((char[]) array.items)[index];
    frame.pushInt(item);
  }

  @Override
  public String format() {
    return "caload";
  }
}