package com.gxk.jvm.instruction.stores;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;

public class IStore3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int tmp = frame.popInt();
    frame.setInt(3, tmp);
  }

  @Override
  public String format() {
    return "istore_3";
  }
}
