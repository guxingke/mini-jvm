package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.NativeMethod;
import java.util.List;
import lombok.Data;

@Data
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
    NativeMethod nm = Heap.findMethod(String.format("%s_%s_%s", clazz, methodName, methodDescriptor));
    if (nm != null) {
      nm.invoke(frame);
      return;
    }

    KClass kClass = Heap.findClass(clazz);
    if (kClass == null) {
      throw new IllegalStateException();
    }

    KMethod method = kClass.getMethod(methodName, methodDescriptor);
    if (method == null) {
      throw new IllegalStateException();
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
          newFrame.localVars.setInt(slotIdx, frame.operandStack.popInt());
          break;
        case "J":
          slotIdx -= 2;
          newFrame.localVars.setLong(slotIdx, frame.operandStack.popLong());
          break;
        case "F":
          slotIdx -= 1;
          newFrame.localVars.setFloat(slotIdx, frame.operandStack.popFloat());
          break;
        case "D":
          slotIdx -= 2;
          newFrame.localVars.setDouble(slotIdx, frame.operandStack.popDouble());
          idx -= 2;
          break;
        default:
          slotIdx--;
          newFrame.localVars.setRef(slotIdx, frame.operandStack.popRef());
          break;
      }
      idx--;
    }

    newFrame.localVars.setRef(0, frame.operandStack.popRef());
    frame.thread.pushFrame(newFrame);
  }
}
