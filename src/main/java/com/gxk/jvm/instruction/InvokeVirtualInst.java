package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.rtda.heap.NativeMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class InvokeVirtualInst implements Instruction {

  public final String clazz;
  public final String methodName;
  public final String methodDescriptor;

  public InvokeVirtualInst(String clazz, String methodName, String methodDescriptor) {
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
    NativeMethod nm = Heap.findMethod(String.format("%s_%s_%s", clazz, methodName, methodDescriptor));
    if (nm != null) {
      nm.invoke(frame);
      return;
    }

    KClass clazz = Heap.findClass(this.clazz);
    KMethod method = clazz.getMethod(methodName, methodDescriptor);

    if (method == null) {
      throw new IllegalStateException();
    }

    if (method.isNative()) {
      throw new IllegalStateException("un impl native method call, " + method);
    }

    // super method
    // fill args
    List<String> args = method.getArgs();
    List<Object> argObjs = new ArrayList<>();
    for (int i = args.size() - 1; i >= 0; i--) {
      String arg = args.get(i);
      switch (arg) {
        case "I":
        case "B":
        case "C":
        case "S":
        case "Z":
          argObjs.add(frame.popInt());
          break;
        case "F":
          argObjs.add(frame.popFloat());
          break;
        case "J":
          argObjs.add(frame.popLong());
          break;
        case "D":
          argObjs.add(frame.popDouble());
          break;
        default:
          argObjs.add(frame.popRef());
          break;
      }
    }

    Collections.reverse(argObjs);

    KObject ref = (KObject) frame.popRef();
    KMethod implMethod = ref.clazz.getMethod(methodName, methodDescriptor);
    Frame newFrame = new Frame(implMethod, frame.thread);

    int slotIdx = 1;
    for (int i = 0; i < args.size(); i++) {
      String arg = args.get(i);
      switch (arg) {
        case "I":
        case "B":
        case "C":
        case "S":
        case "Z":
          newFrame.setInt(slotIdx, (Integer) argObjs.get(i));
          break;
        case "J":
          newFrame.setLong(slotIdx, (Long) argObjs.get(i));
          slotIdx++;
          break;
        case "F":
          newFrame.setFloat(slotIdx, (Float) argObjs.get(i));
          break;
        case "D":
          newFrame.setDouble(slotIdx, (Double) argObjs.get(i));
          slotIdx++;
          break;
        default:
          newFrame.setRef(slotIdx, argObjs.get(i));
          break;
      }
      slotIdx++;
    }

    newFrame.setRef(0, ref);
    frame.thread.pushFrame(newFrame);
  }

  @Override
  public String toString() {
    return "InvokeVirtualInst{" +
        "clazz='" + clazz + '\'' +
        ", methodName='" + methodName + '\'' +
        ", methodDescriptor='" + methodDescriptor + '\'' +
        '}';
  }
}
