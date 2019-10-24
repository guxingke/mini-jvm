package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class InstanceofInst implements Instruction {

  public final String clazz;

  @Override
  public void execute(Frame frame) {
    throw new UnsupportedOperationException("InstanceofInst");
  }
}
