package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

/**
 * @see IConst0Inst
 */
public class IConst1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushInt(1);
  }
}
