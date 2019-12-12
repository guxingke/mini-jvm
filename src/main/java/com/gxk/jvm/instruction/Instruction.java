package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public interface Instruction {

  default int offset() {
    return 1;
  }

  void execute(Frame frame);

  default void debug(String space){
    System.out.println(space + this.toString());
  }

  default String format() {
    return this.getClass().getSimpleName();
  }
}
