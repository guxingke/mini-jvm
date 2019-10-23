package com.gxk.jvm.rtda.heap;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import lombok.Data;

import java.util.Map;

@Data
public class KMethod {

  public final int accessFlags;
  public final String name;
  public final String descriptor;

  public final int maxStacks;
  public final int maxLocals;
  public final Map<Integer, Instruction> instructionMap;

  public KClass clazz;

  @Override
  public String toString() {
    return "KMethod{" +
        "accessFlags=" + accessFlags +
        ", name='" + name + '\'' +
        ", descriptor='" + descriptor + '\'' +
        ", maxStacks=" + maxStacks +
        ", maxLocals=" + maxLocals +
        ", instructionMap=" + instructionMap +
        ", clazz=" + clazz.getName() +
        '}';
  }

  public boolean isNative() {
    return (this.accessFlags & 0x0100) != 0;
  }

  public void invokeNative(Frame frame) {
    KObject thisObj = (KObject) frame.operandStack.popRef();
    NativeMethod method = Heap.findMethod(String.format("%s_%s_%s", clazz.name, name, descriptor));
    Object ret = method.invoke(thisObj);
    switch (descriptor) {
      case "()V":
        return;
      case "()I":
        frame.operandStack.pushInt(((int) ret));
        return;
      case "()J":
        frame.operandStack.pushLong(((long) ret));
        return;
      default:
        frame.operandStack.pushRef(ret);
        return;
    }
  }

  public void invokeStaticNative(Frame frame) {
    NativeMethod method = Heap.findMethod(String.format("%s_%s_%s", clazz.name, name, descriptor));
    Object ret = method.invoke();
    switch (descriptor) {
      case "()V":
        return;
      case "()I":
        frame.operandStack.pushInt(((int) ret));
        return;
      default:
        frame.operandStack.pushRef(ret);
    }
  }
}
