package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class ALoadInst implements Instruction {
  public final int index;

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.localVars.getRef(index);
    frame.operandStack.pushRef(tmp);
  }
}