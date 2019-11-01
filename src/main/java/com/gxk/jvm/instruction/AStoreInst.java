package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class AStoreInst implements Instruction {

  public final int index;

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.operandStack.popRef();
    frame.localVars.setRef(index, tmp);
  }
}