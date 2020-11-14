package com.gxk.jvm.instruction.references;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.PrimitiveArray;

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
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    int count = frame.popInt();

    switch (type) {
      case 4:
        frame.pushRef(PrimitiveArray.boolArray(count));
        return;
      case 5:
        frame.pushRef(PrimitiveArray.charArray(count));
        return;
      case 6:
        frame.pushRef(PrimitiveArray.floatArray(count));
        return;
      case 7:
        frame.pushRef(PrimitiveArray.doubleArray(count));
        return;
      case 8:
        frame.pushRef(PrimitiveArray.byteArray(count));
        return;
      case 9:
        frame.pushRef(PrimitiveArray.shortArray(count));
        return;
      case 10:
        frame.pushRef(PrimitiveArray.intArray(count));
        return;
      case 11:
        frame.pushRef(PrimitiveArray.longArray(count));
        return;
      default:
        throw new IllegalStateException();
    }
  }

  @Override
  public String format() {
    return "newarray " + type;
  }

}