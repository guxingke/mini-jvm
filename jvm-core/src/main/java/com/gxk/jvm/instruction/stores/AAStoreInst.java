package com.gxk.jvm.instruction.stores;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.InstanceArray;

public class AAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object val = frame.popRef();
    Integer index = frame.popInt();
    InstanceArray array = (InstanceArray) frame.popRef();
    array.items[index] = val;
  }

  @Override
  public String format() {
    return "aastore";
  }
}