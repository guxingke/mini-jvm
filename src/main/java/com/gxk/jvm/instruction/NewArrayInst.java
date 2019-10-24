package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;
import lombok.Data;

@Data
public class NewArrayInst implements Instruction {
  public final int type;

  @Override
  public void execute(Frame frame) {
    Integer count = frame.operandStack.popInt();
    switch (type) {
      case 5:
        frame.operandStack.pushRef(new KArray(null, new Character[count]));
        return;
      default:
        throw new UnsupportedOperationException(String.valueOf(type));
    }
  }
}
