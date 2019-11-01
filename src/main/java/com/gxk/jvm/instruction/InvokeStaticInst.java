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
    Frame newFrame = new Frame(method, frame.thread);
    // fill args
    List<String> args = method.getArgs();
    int idx = args.size() - 1;
    while (idx >= 0) {
      String arg = args.get(idx);
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

