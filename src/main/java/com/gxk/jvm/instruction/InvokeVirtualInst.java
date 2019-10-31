package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.rtda.heap.NativeMethod;
import java.util.List;


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

    KMethod method = Heap.findClass(clazz).getMethod(methodName, methodDescriptor);

    if (method == null) {
      throw new IllegalStateException();
    }

    Frame newFrame = new Frame(method, frame.thread);
    // fill args
    List<String> args = method.getArgs();
    int idx = args.size();
    while (idx > 0) {
      String arg = args.get(idx - 1);
      switch (arg) {
        case "I":
        case "B":
        case "C":
        case "S":
          newFrame.localVars.setInt(idx, frame.operandStack.popInt());
          idx--;
          break;
        case "J":
          newFrame.localVars.setLong(idx, frame.operandStack.popLong());
          idx -= 2;
          break;
        case "F":
          newFrame.localVars.setFloat(idx, frame.operandStack.popFloat());
          idx--;
          break;
        case "D":
          newFrame.localVars.setDouble(idx, frame.operandStack.popDouble());
          idx -= 2;
          break;
        default:
          newFrame.localVars.setRef(idx, frame.operandStack.popRef());
          idx--;
          break;
      }
    }

    newFrame.localVars.setRef(0, frame.operandStack.popRef());
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
