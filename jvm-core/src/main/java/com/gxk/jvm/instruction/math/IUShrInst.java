package com.gxk.jvm.instruction.math;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class IUShrInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer v2 = frame.popInt();
    Integer v1 = frame.popInt();
    int s = v2 & 0x1f;

    if (v1 >= 0) {
      int ret = v1 >> s;
      frame.pushInt(ret);
      return;
    }
    int ret = (v1 >> s) + (2 << ~s);
    frame.pushInt(ret);
  }

  @Override
  public String format() {
    return "iushr";
  }
}