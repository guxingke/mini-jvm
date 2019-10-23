package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvokeInterfaceInst implements Instruction {

  public final String clazzName;
  public final String methodName;
  public final String descriptor;

  public final int count;
  public final int zero;

  @Override
  public int offset() {
    return 5;
  }

  @Override
  public void execute(Frame frame) {
    throw new UnsupportedOperationException();
  }
}

