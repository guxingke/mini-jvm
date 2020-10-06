package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KObject;

public class AALoadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    KObject item = ((KObject[]) array.items)[index];
    frame.pushRef(item);
  }

  @Override
  public String format() {
    return "aaload";
  }
}
