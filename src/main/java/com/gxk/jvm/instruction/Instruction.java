package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public interface Instruction {
  default int offset() {
    return 1;
  }

  default void fetchOperands() {

  }

  void execute(Frame frame);

  default void debug(Frame frame) {
    System.out.println(this.getClass());
    frame.debug();
  }
}
