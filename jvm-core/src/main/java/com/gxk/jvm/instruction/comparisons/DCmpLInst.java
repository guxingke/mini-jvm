package com.gxk.jvm.instruction.comparisons;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;

public class DCmpLInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Double v2 = frame.popDouble();
    Double v1 = frame.popDouble();
    if (v1.equals(v2)) {
      frame.pushInt(0);
      return;
    }
    if (v1 < v2) {
      frame.pushInt(-1);
      return;
    }
    frame.pushInt(1);
  }
}