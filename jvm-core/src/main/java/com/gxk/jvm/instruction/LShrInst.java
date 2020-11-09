package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LShrInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    final int v2 = frame.popInt();
    final long v1 = frame.popLong();
    frame.pushLong(v1 >> v2);
  }
}