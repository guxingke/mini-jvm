package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KClass;
import com.gxk.jvm.rtda.memory.KMethod;
import com.gxk.jvm.rtda.memory.NativeMethod;
import com.gxk.jvm.util.Utils;
import java.util.List;

public class InvokeSpecialInst implements Instruction {

  public final String clazz;
  public final String methodName;
  public final String methodDescriptor;

  public InvokeSpecialInst(String clazz, String methodName, String methodDescriptor) {
    this.clazz = clazz;
    this.methodName = methodName;
    this.methodDescriptor = methodDescriptor;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    NativeMethod nm = MethodArea
        .findMethod(Utils.genNativeMethodKey(clazz, methodName, methodDescriptor));
    if (nm != null) {
      nm.invoke(frame);
      return;
    }

    KClass kClass = MethodArea.findClass(clazz);
    if (kClass == null) {
      throw new IllegalStateException();
    }

    KMethod method = kClass.getMethod(methodName, methodDescriptor);
    if (method == null) {
      System.out.println(Utils.genNativeMethodKey(clazz, methodName, methodDescriptor));
      throw new IllegalStateException();
    }

    if (method.isNative()) {
      throw new IllegalStateException("un impl native method call, " + method);
    }

    Frame newFrame = new Frame(method, frame.thread);
    // fill args
    List<String> args = method.getArgs();
    int slotIdx = method.getArgSlotSize() + 1;

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

    newFrame.setRef(0, frame.popRef());
    frame.thread.pushFrame(newFrame);
  }

  @Override
  public String format() {
    return "invokespecail " + clazz + " " + methodName + " " + methodDescriptor;
  }
}
