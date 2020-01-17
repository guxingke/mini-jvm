package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public interface Instruction {

  default int offset() {
    return 1;
  }

  void execute(Frame frame);

  default String debug(String prefix){
    return prefix + this.format();
  }

  default String format() {
    return this.getClass().getSimpleName();
  }
}
