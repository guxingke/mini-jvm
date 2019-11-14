package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.NativeMethod;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvokeStaticInst implements Instruction {

  public final String clazzName;
  public final String methodName;
  public final String descriptor;

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    NativeMethod nm = Heap.findMethod(String.format("%s_%s_%s", clazzName, methodName, descriptor));
    if (nm != null) {
      nm.invoke(frame);
      return;
    }

    KClass kClass = Heap.findClass(clazzName);
    if (kClass == null) {
      kClass = frame.method.clazz.getClassLoader().loadClass(clazzName);
    }

    if (!kClass.isStaticInit()) {
      KMethod cinit = kClass.getMethod("<clinit>", "()V");
      if (cinit == null) {
        throw new IllegalStateException();
      }

      Frame newFrame = new Frame(cinit, frame.thread);
      kClass.setStaticInit(1);
      KClass finalKClass = kClass;
      newFrame.setOnPop(() -> finalKClass.setStaticInit(2));
      frame.thread.pushFrame(newFrame);

      frame.nextPc = frame.thread.getPc();
      return;
    }

    KMethod method = kClass.getMethod(methodName, descriptor);

    if (method.isNative()) {
      throw new IllegalStateException("un impl native method call, " + method);
    }

    Frame newFrame = new Frame(method, frame.thread);
    // fill args
    List<String> args = method.getArgs();
    int slotIdx = method.getArgSlotSize();

    int idx = args.size() - 1;
    while (idx >= 0) {
      String arg = args.get(idx);
      switch (arg) {
        case "I":
        case "B":
        case "C":
        case "S":
        case "Z":
          slotIdx--;
          newFrame.setInt(slotIdx, frame.popInt());
          break;
        case "J":
          slotIdx -= 2;
          newFrame.setLong(slotIdx, frame.popLong());
          break;
        case "F":
          slotIdx -= 1;
          newFrame.setFloat(slotIdx, frame.popFloat());
          break;
        case "D":
          slotIdx -= 2;
          newFrame.setDouble(slotIdx, frame.popDouble());
          idx -= 2;
          break;
        default:
          slotIdx--;
          newFrame.setRef(slotIdx, frame.popRef());
          break;
      }
      idx--;
    }
    frame.thread.pushFrame(newFrame);
  }

  @Override
  public String toString() {
    return "InvokeStaticInst{" +
        "clazzName='" + clazzName + '\'' +
        ", methodName='" + methodName + '\'' +
        ", descriptor='" + descriptor + '\'' +
        '}';
  }
}

