package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KClass;

import java.util.HashMap;
import java.util.Map;

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
  private static Map<Integer, String> MAPPING = new HashMap<>(8);
  static {
    MAPPING.put(4, "java/lang/Boolean");
    MAPPING.put(5, "java/lang/Character");
    MAPPING.put(6, "java/lang/Float");
    MAPPING.put(7, "java/lang/Double");
    MAPPING.put(8, "java/lang/Byte");
    MAPPING.put(9, "java/lang/Short");
    MAPPING.put(10, "java/lang/Integer");
    MAPPING.put(11, "java/lang/Long");
  }

  public final int type;
  private final String clazz;

  public NewArrayInst(int type) {
    this.type = type;
    this.clazz = MAPPING.get(type);
  }

  @Override
  public void execute(Frame frame) {
    Integer count = frame.popInt();
    if (this.clazz == null) {
      throw new UnsupportedOperationException(String.valueOf(type));
    }

    KClass clazz = Heap.findClass(this.clazz);
    switch (type) {
      case 4:
        frame.pushRef(new KArray(clazz, new Boolean[count]));
        return;
      case 5:
        frame.pushRef(new KArray(clazz, new Character[count]));
        return;
      case 6:
        frame.pushRef(new KArray(clazz, new Float[count]));
        return;
      case 7:
        frame.pushRef(new KArray(clazz, new Double[count]));
        return;
      case 8:
        frame.pushRef(new KArray(clazz, new Byte[count]));
        return;
      case 9:
        frame.pushRef(new KArray(clazz, new Short[count]));
        return;
      case 10:
        frame.pushRef(new KArray(clazz, new Integer[count]));
        return;
      case 11:
        frame.pushRef(new KArray(clazz, new Long[count]));
        return;
      default:
        throw new IllegalStateException();
    }
  }

  @Override
  public String format() {
    return "newarray " + clazz;
  }

}