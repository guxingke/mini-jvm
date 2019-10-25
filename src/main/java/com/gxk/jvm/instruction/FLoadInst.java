package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class FLoadInst implements Instruction {
  public final int index;

  @Override
  public void execute(Frame frame) {
    float tmp = frame.localVars.getFloat(index);
    frame.operandStack.pushFloat(tmp);
  }
}