package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.KClass;
import com.gxk.jvm.rtda.memory.KField;
import com.gxk.jvm.rtda.memory.KObject;
import com.gxk.jvm.rtda.memory.MethodArea;

public class LdcInst implements Instruction {

  public final String descriptor;
  public final Object val;

  @Override
  public int offset() {
    return 2;
  }

  public LdcInst(String descriptor, Object val) {
    this.descriptor = descriptor;
    this.val = val;
  }

  @Override
  public void execute(Frame frame) {
    switch (descriptor) {
      case "I":
        frame.pushInt(((Integer) val));
        break;
      case "F":
        frame.pushFloat(((float) val));
        break;
      case "Ljava/lang/String":
        KClass klass = MethodArea.findClass("java/lang/String");
        if (klass == null) {
          klass = frame.method.clazz.classLoader.loadClass("java/lang/String");
        }
        if (!klass.isStaticInit()) {
          Frame newFrame = new Frame(klass.getMethod("<clinit>", "()V"), frame.thread);
          klass.setStaticInit(1);
          KClass finalKlass = klass;
          newFrame.setOnPop(() -> finalKlass.setStaticInit(2));
          frame.thread.pushFrame(newFrame);

          frame.nextPc = frame.thread.getPc();
          return;
        }
        Long offset = klass.newObject();
        KObject object = Heap.load(offset);
        KField field = object.getField("value", "[C");
        KClass arrClazz = new KClass(1, "[C", frame.method.clazz.classLoader, null);

        char[] chars = val.toString().toCharArray();
        Character[] characters = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
          characters[i] = chars[i];
        }
        Long arrOffset = KArray.newArray(arrClazz, characters);
        field.val = new Slot[]{new Slot(arrOffset)};
        frame.pushRef(offset);
        break;
      case "L":
        KClass klass2 = MethodArea.findClass(val.toString());
        if (klass2 == null) {
          klass2 = frame.method.clazz.classLoader.loadClass(val.toString());
        }
        frame.pushRef(klass2.getRuntimeClass());
        break;
      default:
        throw new IllegalStateException();
//        frame.pushRef(val);
//        break;
    }
  }

  @Override
  public String format() {
    return "ldc " + descriptor + " " + val;
  }

  @Override
  public String toString() {
    return "LdcInst{" +
        "descriptor='" + descriptor + '\'' +
        ", val=" + val +
        '}';
  }
}
