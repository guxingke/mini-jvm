package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;

public class NewArrayInst implements Instruction {
  public final int type;

  public NewArrayInst(int type) {
    this.type = type;
  }

  @Override
  public void execute(Frame frame) {
    Integer count = frame.popInt();
    switch (type) {
      case 5:
        frame.pushRef(new KArray(null, new Character[count]));
        return;
      case 10:
        frame.pushRef(new KArray(Heap.findClass("java/lang/Integer"), new Integer[count]));
        return;
      default:
        throw new UnsupportedOperationException(String.valueOf(type));
    }
  }
}