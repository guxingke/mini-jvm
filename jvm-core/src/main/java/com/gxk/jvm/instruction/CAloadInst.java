package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.KArray;

public class CAloadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    char item = (char) array.items[index];
    frame.pushInt(((int) (item)));
  }

  @Override
  public String format() {
    return "caload";
  }
}