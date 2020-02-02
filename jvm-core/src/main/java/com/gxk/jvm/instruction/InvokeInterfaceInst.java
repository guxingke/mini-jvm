package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.rtda.heap.NativeMethod;
import com.gxk.jvm.util.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvokeInterfaceInst implements Instruction {

  public final String clazzName;
  public final String methodName;
  public final String methodDescriptor;

  public final int count;
  public final int zero;

  public InvokeInterfaceInst(String clazzName, String methodName, String methodDescriptor, int count, int zero) {
    this.clazzName = clazzName;
    this.methodName = methodName;
    this.methodDescriptor = methodDescriptor;
    this.count = count;
    this.zero = zero;
  }

  @Override
  public int offset() {
    return 5;
  }

  @Override
  public void execute(Frame frame) {
    NativeMethod nm = Heap.findMethod(Utils.genNativeMethodKey( clazzName, methodName, methodDescriptor));
    if (nm != null) {
      nm.invoke(frame);
      return;
    }

    KClass clazz = Heap.findClass(this.clazzName);
    if (clazz == null) {
      clazz = frame.method.clazz.classLoader.loadClass(clazzName);
    }

    if (!clazz.isStaticInit()) {
      KMethod cinit = clazz.getClinitMethod();
      if (cinit == null) {
        throw new IllegalStateException();
      }

      // hack by native method
      String key =Utils.genNativeMethodKey( cinit.clazz.name, cinit.name, cinit.descriptor);
      NativeMethod ciNm = Heap.findMethod(key);
      if (ciNm != null) {
        ciNm.invoke(frame);
      } else {
        Frame newFrame = new Frame(cinit, frame.thread);
        clazz.setStaticInit(1);
        KClass finalClass = clazz;
        newFrame.setOnPop(() -> finalClass.setStaticInit(2));
        frame.thread.pushFrame(newFrame);

        frame.nextPc = frame.thread.getPc();
        return;
      }
    }

    KMethod method = clazz.getMethod(methodName, methodDescriptor);

    if (method == null) {
      // try find interfaces
      if (clazz.interfaceNames.isEmpty()) {
        throw new IllegalStateException();
      }

      // already load interface
      if (!clazz.getInterfaces().isEmpty()) {
        for (KClass intClass : clazz.getInterfaces()) {
          method= intClass.getMethod(methodName, methodDescriptor);
          if (method != null) {
            break;
          }
        }
      } else {
        clazz.interfaceInit(frame);
        return;
      }
    }

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
      argObjs.add(Utils.pop(frame, arg));
    }

    KObject ref = (KObject) frame.popRef();
    KMethod implMethod = ref.clazz.getMethod(methodName, methodDescriptor);
    // method is default method
    if (implMethod == null) {
      implMethod = method;
    }

    // hack for lambda
    nm = Heap.findMethod(Utils.genNativeMethodKey(implMethod.clazz.name, implMethod.name, implMethod.descriptor));
    if (nm != null) {
      // restore frame
      ArrayList<String> tmpArgs = new ArrayList<>(args);
      Collections.reverse(tmpArgs);

      frame.pushRef(ref);
      for (int i = 0; i < tmpArgs.size(); i++) {
        String arg = tmpArgs.get(i);
        Object obj = argObjs.get(argObjs.size() - 1 - i);
        Utils.push(frame, arg, obj);
      }

      nm.invoke(frame);
      return;
    }

    Collections.reverse(argObjs);

    Frame newFrame = new Frame(implMethod, frame.thread);

    int slotIdx = 1;
    for (int i = 0; i < args.size(); i++) {
      String arg = args.get(i);
      slotIdx += Utils.setLocals(newFrame, slotIdx, arg, argObjs.get(i));
    }

    newFrame.setRef(0, ref);
    frame.thread.pushFrame(newFrame);
  }

  @Override
  public String format() {
    return "invokeinterface " + clazzName + " " + methodName + " " + methodDescriptor;
  }

}

