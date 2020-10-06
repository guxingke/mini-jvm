package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KObject;

public class AAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    KObject val = frame.popRef();
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    ((KObject[]) array.items)[index] = val;
  }

  @Override
  public String format() {
    return "aastore";
  }
}