package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.Stack;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.rtda.heap.NativeMethod;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class InvokeInterfaceInst implements Instruction {

  public final String clazzName;
  public final String methodName;
  public final String methodDescriptor;

  public final int count;
  public final int zero;

  @Override
  public int offset() {
    return 5;
  }

  @Override
  public void execute(Frame frame) {
    NativeMethod nm = Heap.findMethod(String.format("%s_%s_%s", clazzName, methodName, methodDescriptor));
    if (nm != null) {
      nm.invoke(frame);
      return;
    }

    KClass clazz = Heap.findClass(this.clazzName);
    if (clazz == null) {
      clazz = frame.method.clazz.getClassLoader().loadClass(clazzName);
    }

    if (!clazz.isStaticInit()) {
      KMethod cinit = clazz.getClinitMethod();
      if (cinit == null) {
        throw new IllegalStateException();
      }

      Frame newFrame = new Frame(cinit, frame.thread);
      clazz.setStaticInit(1);
      KClass finalClass = clazz;
      newFrame.setOnPop(() -> finalClass.setStaticInit(2));
      frame.thread.pushFrame(newFrame);

      frame.nextPc = frame.thread.getPc();
      return;
    }

    KMethod method = clazz.getMethod(methodName, methodDescriptor);

    if (method == null) {
      throw new IllegalStateException();
    }

    if (method.isNative()) {
      throw new IllegalStateException("un impl native method call, " + method);
    }

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

    KObject ref = (KObject) frame.popRef();
    KMethod implMethod = ref.clazz.getMethod(methodName, methodDescriptor);
    // method is default method
    if (implMethod == null) {
      implMethod = method;
    }

    // hack for lambda
    String key = String.format("%s_%s_%s", implMethod.clazz.getName(), implMethod.getName(), implMethod.getDescriptor());
    nm = Heap.findMethod(key);
    if (nm != null) {
      // restore frame
      ArrayList<String> tmpArgs = new ArrayList<>(args);
      Collections.reverse(tmpArgs);

      frame.pushRef(ref);
      for (int i = 0; i < tmpArgs.size(); i++) {
        String arg = tmpArgs.get(i);
        Object obj = argObjs.get(argObjs.size() - 1 - i);
        switch (arg) {
          case "I":
          case "B":
          case "C":
          case "S":
          case "Z":
            frame.pushInt(((Integer) obj));
            break;
          case "J":
            frame.pushLong(((Long) obj));
            break;
          case "F":
            frame.pushFloat(((Float) obj));
            break;
          case "D":
            frame.pushDouble(((Double) obj));
            break;
          default:
            frame.pushRef(obj);
            break;
        }
      }

      nm.invoke(frame);
      return;
    }

    Collections.reverse(argObjs);

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
}

