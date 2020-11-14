package com.gxk.jvm.instruction.stack;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;

public class Dup2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    final Slot v2 = frame.pop();
    final Slot v1 = frame.pop();
    frame.push(v1);
    frame.push(v2);
    frame.push(v1);
    frame.push(v2);
  }
}
