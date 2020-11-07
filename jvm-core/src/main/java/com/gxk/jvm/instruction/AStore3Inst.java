package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KObject;

public class AStore3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.popRef();
    frame.setRef(3, (KObject) tmp);
  }

  @Override
  public String format() {
    return "astore_3";
  }

}
