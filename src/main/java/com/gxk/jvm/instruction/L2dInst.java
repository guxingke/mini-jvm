package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class L2dInst implements Instruction {

  @Override
  public int offset() {
    return 1;
  }

  @Override
  public void execute(Frame frame) {
    long tmp = frame.operandStack.popLong();
    frame.operandStack.pushDouble((double) tmp);
  }
}