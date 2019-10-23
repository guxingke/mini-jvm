package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;

import java.util.Objects;

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
    if (Objects.equals(clazz, "java/io/PrintStream")) {
      // 暂时只执行 sout
      Object obj = frame.operandStack.peekRef();
      if (obj != null) {
        System.out.println(frame.operandStack.popRef());
        return;
      }

      // TODO 暂时只支持 int
      System.out.println(frame.operandStack.popInt());
      return;
    }

    Object thisObj = frame.operandStack.popRef();
    KObject obj = (KObject) thisObj;
    KMethod method = obj.getMethod(methodName, methodDescriptor);

    if (method == null) {
      throw new IllegalStateException();
    }

    if (method.isNative()) {
      try {
        frame.operandStack.pushRef(obj);
        method.invokeNative(frame);
        return;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    Frame newFrame = new Frame(method, frame.thread);
    if (method.getDescriptor().startsWith("()")) {
      newFrame.localVars.setRef(0, thisObj);
      frame.thread.pushFrame(newFrame);
      return;
    }

    if (method.getDescriptor().startsWith("(I)")) {
      Integer arg2 = frame.operandStack.popInt();
      newFrame.localVars.setRef(0, thisObj);
      newFrame.localVars.setInt(1, arg2);
      frame.thread.pushFrame(newFrame);
      return;
    }
  }
}
