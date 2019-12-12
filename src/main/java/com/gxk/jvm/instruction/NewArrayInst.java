package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;

/*
primitiveArrayInfo(4,  0'Z, boolean, int).
primitiveArrayInfo(5,  0'C, char,    int).
primitiveArrayInfo(6,  0'F, float,   float).
primitiveArrayInfo(7,  0'D, double,  double).
primitiveArrayInfo(8,  0'B, byte,    int).
primitiveArrayInfo(9,  0'S, short,   int).
primitiveArrayInfo(10, 0'I, int,     int).
primitiveArrayInfo(11, 0'J, long,    long).
 */
public class NewArrayInst implements Instruction {
  public final int type;

  public NewArrayInst(int type) {
    this.type = type;
  }

  @Override
  public void execute(Frame frame) {
    Integer count = frame.popInt();
    switch (type) {
      case 4:
        frame.pushRef(new KArray(Heap.findClass("java/lang/Boolean"), new Boolean[count]));
        return;
      case 5:
        frame.pushRef(new KArray(Heap.findClass("java/lang/Character"), new Character[count]));
        return;
      case 6:
        frame.pushRef(new KArray(Heap.findClass("java/lang/Float"), new Float[count]));
        return;
      case 7:
        frame.pushRef(new KArray(Heap.findClass("java/lang/Double"), new Double[count]));
        return;
      case 8:
        frame.pushRef(new KArray(Heap.findClass("java/lang/Byte"), new Byte[count]));
        return;
      case 9:
        frame.pushRef(new KArray(Heap.findClass("java/lang/Short"), new Short[count]));
        return;
      case 10:
        frame.pushRef(new KArray(Heap.findClass("java/lang/Integer"), new Integer[count]));
        return;
      case 11:
        frame.pushRef(new KArray(Heap.findClass("java/lang/Long"), new Long[count]));
        return;
      default:
        throw new UnsupportedOperationException(String.valueOf(type));
    }
  }
}