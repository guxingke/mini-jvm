package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class WideInst implements Instruction {

  public final int offset;
  public final Instruction inst;

  @Override
  public int offset() {
    return offset;
  }

  @Override
  public void execute(Frame frame) {
    inst.execute(frame);
  }
}
