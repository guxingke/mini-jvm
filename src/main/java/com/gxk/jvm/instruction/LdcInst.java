package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KObject;

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
        frame.operandStack.pushInt(((Integer) val));
        break;
      case "F":
        frame.operandStack.pushFloat(((float) val));
      case "Ljava/lang/String":
        KClass klass = Heap.findClass("java/lang/String");
        if (klass == null) {
          klass = frame.method.clazz.getClassLoader().loadClass("java/lang/String");
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
        KObject object = klass.newObject();
        KField field = object.getField("value", "[C");
        KClass arrClazz = new KClass("[C", frame.method.clazz.classLoader);

        char[] chars = val.toString().toCharArray();
        Character[] characters = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
          characters[i] = chars[i];
        }
        KArray arr = new KArray(arrClazz, characters);
        field.val = new Slot[]{new Slot(arr)};
        frame.operandStack.pushRef(object);
        break;
      case "L":
        KClass klass2 = Heap.findClass(val.toString());
        if (klass2 == null) {
          klass2 = frame.method.clazz.getClassLoader().loadClass(val.toString());
        }
        if (!klass2.isStaticInit()) {
          Frame newFrame = new Frame(klass2.getMethod("<clinit>", "()V"), frame.thread);
          klass2.setStaticInit(1);
          KClass finalKlass = klass2;
          newFrame.setOnPop(() -> finalKlass.setStaticInit(2));
          frame.thread.pushFrame(newFrame);

          frame.nextPc = frame.thread.getPc();
          return;
        }
        frame.operandStack.pushRef(klass2);
        break;
      default:
        frame.operandStack.pushRef(val);
        break;
    }
  }

  @Override
  public String toString() {
    return "LdcInst{" +
        "descriptor='" + descriptor + '\'' +
        ", val=" + val +
        '}';
  }
}
