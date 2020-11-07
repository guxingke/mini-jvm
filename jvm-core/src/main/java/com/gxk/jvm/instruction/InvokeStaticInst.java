package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Class;
import com.gxk.jvm.rtda.heap.Method;
import com.gxk.jvm.rtda.heap.NativeMethod;

import com.gxk.jvm.util.Utils;
import java.util.List;

public class InvokeStaticInst implements Instruction {

  public final String clazzName;
  public final String methodName;
  public final String descriptor;

  public InvokeStaticInst(String clazzName, String methodName, String descriptor) {
    this.clazzName = clazzName;
    this.methodName = methodName;
    this.descriptor = descriptor;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    NativeMethod nm = Heap.findMethod(Utils.genNativeMethodKey( clazzName, methodName, descriptor));
    if (nm != null) {
      nm.invoke(frame);
      return;
    }

    Class aClass = Heap.findClass(clazzName);
    if (aClass == null) {
      aClass = frame.method.clazz.classLoader.loadClass(clazzName);
    }

    if (!aClass.getStat()) {
      Method cinit = aClass.getMethod("<clinit>", "()V");
      if (cinit == null) {
        throw new IllegalStateException();
      }

      Frame newFrame = new Frame(cinit);
      aClass.setStat(1);
      Class finalClass = aClass;
      newFrame.setOnPop(() -> finalClass.setStat(2));
      frame.thread.pushFrame(newFrame);

      frame.nextPc = frame.getPc();
      return;
    }

    Method method = aClass.getMethod(methodName, descriptor);

    if (method.isNative()) {
      throw new IllegalStateException("un impl native method call, " + method);
    }

    Frame newFrame = new Frame(method);
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
  public String format() {
    return "invokestatic " + clazzName + " " + methodName + " " + descriptor;
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

