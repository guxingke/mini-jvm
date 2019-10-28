package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class D2fInst implements Instruction {

  @Override
  public int offset() {
    return 1;
  }

  @Override
  public void execute(Frame frame) {
    double tmp = frame.operandStack.popDouble();
    frame.operandStack.pushFloat(((float) tmp));
  }
}