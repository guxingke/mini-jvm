package com.gxk.jvm.instruction.constants;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.UnionSlot;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.InstanceArray;
import com.gxk.jvm.rtda.heap.Class;
import com.gxk.jvm.rtda.heap.Field;
import com.gxk.jvm.rtda.heap.Instance;

public class LdcWInst implements Instruction {
  public final String descriptor;
  public final Object val;

  @Override
  public int offset() {
    return 3;
  }

  public LdcWInst(String descriptor, Object val) {
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
        Class klass = Heap.findClass("java/lang/String");
        if (klass == null) {
          klass = frame.method.clazz.classLoader.loadClass("java/lang/String");
        }
        if (!klass.getStat()) {
          Frame newFrame = new Frame(klass.getMethod("<clinit>", "()V"));
          klass.setStat(1);
          Class finalKlass = klass;
          newFrame.setOnPop(() -> finalKlass.setStat(2));
          frame.thread.pushFrame(newFrame);

          frame.nextPc = frame.getPc();
          return;
        }
        Instance object = klass.newInstance();
        Field field = object.getField("value", "[C");
        Class arrClazz = new Class(1, "[C", frame.method.clazz.classLoader, null);

        char[] chars = val.toString().toCharArray();
        Character[] characters = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
          characters[i] = chars[i];
        }
        InstanceArray arr = new InstanceArray(arrClazz, characters);
        field.val = UnionSlot.of(arr);
        frame.pushRef(object);
        break;
      default:
        frame.pushRef((Instance) val);
        break;
    }
  }

  @Override
  public String format() {
    return "ldcw " + descriptor + " " + val;
  }
}
