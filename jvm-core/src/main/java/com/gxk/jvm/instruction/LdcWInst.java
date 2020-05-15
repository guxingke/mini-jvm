package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.KClass;
import com.gxk.jvm.rtda.memory.KField;

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
        Long object = klass.newObject();
        KField field = Heap.load(object).getField("value", "[C");
        KClass arrClazz = new KClass(1, "[C", frame.method.clazz.classLoader, null);

        char[] chars = val.toString().toCharArray();
        Character[] characters = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
          characters[i] = chars[i];
        }
        Long arrOff = KArray.newArray(arrClazz, characters);
        field.val = new Slot[] {new Slot(arrOff)};
        frame.pushRef(object);
        break;
      default:
        throw new IllegalStateException();
//        frame.pushRef(val);
//        break;
    }
  }

  @Override
  public String format() {
    return "ldcw " + descriptor + " " + val;
  }
}
