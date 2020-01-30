package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IAndInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer v2 = frame.popInt();
    Integer v1 = frame.popInt();
    int val = v1 & v2;
    frame.pushInt(val);
  }
}