package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
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
    NativeMethod nm= Heap.findMethod(String.format("%s_%s_%s", clazz, methodName, methodDescriptor));
    if (nm != null) {
      nm.invoke(frame);
      return;
    }

    Object thisObj = frame.operandStack.popRef();
    KObject obj = (KObject) thisObj;
    KMethod method = obj.getMethod(methodName, methodDescriptor);

    if (method == null) {
      throw new IllegalStateException();
    }

    Frame newFrame = new Frame(method, frame.thread);
    // fill args
    int idx = 0;
    for (String arg : method.getArgs()) {
      switch (arg) {
        case "I":
        case "B":
        case "C":
        case "S":
          newFrame.localVars.setInt(idx, frame.operandStack.popInt());
          idx++;
          break;
        case "J":
          newFrame.localVars.setLong(idx, frame.operandStack.popLong());
          idx += 2;
          break;
        case "F":
          newFrame.localVars.setFloat(idx, frame.operandStack.popFloat());
          idx++;
          break;
        case "D":
          newFrame.localVars.setDouble(idx, frame.operandStack.popDouble());
          idx += 2;
          break;
        default:
          idx++;
          newFrame.localVars.setRef(idx, frame.operandStack.popRef());
          break;
      }
    }
    newFrame.localVars.setRef(method.getArgs().size(), thisObj);
    if (idx != method.getArgs().size()) {
      throw new IllegalStateException();
    }
    frame.thread.pushFrame(newFrame);
  }
}
