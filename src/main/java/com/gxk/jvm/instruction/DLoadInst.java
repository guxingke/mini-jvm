package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class DLoadInst implements Instruction {
  public final int index;

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    double tmp = frame.localVars.getDouble(index);
    frame.operandStack.pushDouble(tmp);
  }
}