package com.gxk.jvm.instruction.references;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Class;
import com.gxk.jvm.rtda.heap.Method;
import com.gxk.jvm.rtda.heap.Instance;
import com.gxk.jvm.rtda.heap.NativeMethod;
import com.gxk.jvm.util.Utils;

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
    if (Objects.equals("sun/misc/Unsafe", clazz)
        || Objects.equals("java/util/Properties", clazz)
        || Objects.equals("java/util/zip/ZipFile", clazz)
    ) {
      NativeMethod nativeMethod = Heap
          .findMethod(Utils.genNativeMethodKey(clazz, methodName, methodDescriptor));
      if (nativeMethod != null) {
        nativeMethod.invoke(frame);
        return;
      }
    }

    Class clazz = Heap.findClass(this.clazz);
    Method method = clazz.getMethod(methodName, methodDescriptor);

    if (method == null) {
      // try find interfaces
      if (clazz.interfaceNames.isEmpty()) {
        System.out.println(this.clazz + " " + this.methodName + " " + this.methodDescriptor);
        throw new IllegalStateException();
      }

      // already load interface
      if (!clazz.getInterfaces().isEmpty()) {
        for (Class intClass : clazz.getInterfaces()) {
          method = intClass.getMethod(methodName, methodDescriptor);
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

    // super method
    // fill args
    final int size = method.getArgSlotSize();
    Instance self = frame.getThis(size);
    Method implMethod = self.clazz.getMethod(methodName, methodDescriptor);

    NativeMethod nm = Heap.findMethod(Utils.genNativeMethodKey(implMethod));
    if (nm != null) {
      nm.invoke(frame);
      return;
    }

    if (implMethod.isNative()) {
      throw new IllegalStateException();
    }
    Utils.invokeMethod(implMethod);
  }

  @Override
  public String format() {
    return "invokevirtual " + clazz + " " + methodName + " " + methodDescriptor;
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
