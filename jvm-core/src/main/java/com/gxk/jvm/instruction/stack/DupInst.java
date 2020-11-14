package com.gxk.jvm.instruction.stack;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;

public class DupInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    final Slot val = frame.pop();
    frame.push(val);
    frame.push(val);
  }

  @Override
  public String format() {
    return "dup";
  }
}
