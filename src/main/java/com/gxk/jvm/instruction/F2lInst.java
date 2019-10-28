package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class F2lInst implements Instruction {

  @Override
  public int offset() {
    return 1;
  }

  @Override
  public void execute(Frame frame) {
    float tmp = frame.operandStack.popFloat();
    frame.operandStack.pushLong((long) tmp);
  }
}