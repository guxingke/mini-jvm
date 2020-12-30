package com.gxk.jvm.instruction.math;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;

public class IShrInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int v2 = frame.popInt();
    int v1 = frame.popInt();
    int s = v2 & 0x1f;
    int ret = v1 >> s;
    frame.pushInt(ret);
  }
}