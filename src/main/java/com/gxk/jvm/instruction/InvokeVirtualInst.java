package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.NativeMethod;

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
    String key = String.format("%s_%s_%s", clazz, methodName, methodDescriptor);
    NativeMethod nm = Heap.findMethod(key);
    if (nm != null) {
      try {
        nm.invoke(frame);
        return;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    KMethod method = Heap.findClass(clazz).getMethod(methodName, methodDescriptor);
    if (method == null) {
      throw new IllegalStateException();
    }

    Frame newFrame = new Frame(method, frame.thread);
    if (method.getDescriptor().startsWith("()")) {
      newFrame.localVars.setRef(0, frame.operandStack.popRef());
      frame.thread.pushFrame(newFrame);
      return;
    }

    if (method.getDescriptor().startsWith("(I)")) {
      Integer arg2 = frame.operandStack.popInt();
      newFrame.localVars.setRef(0, frame.operandStack.popRef());
      newFrame.localVars.setInt(1, arg2);
      frame.thread.pushFrame(newFrame);
      return;
    }

    if (method.getDescriptor().startsWith("(Ljava/lang/String;)V")) {
      Object obj = frame.operandStack.popRef();
      newFrame.localVars.setRef(0, frame.operandStack.popRef());
      newFrame.localVars.setRef(1, obj);
      frame.thread.pushFrame(newFrame);
      return;
    }

  }
}
