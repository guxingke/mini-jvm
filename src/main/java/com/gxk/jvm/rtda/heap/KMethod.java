package com.gxk.jvm.rtda.heap;

import com.gxk.jvm.classfile.Exception;
import com.gxk.jvm.classfile.ExceptionTable;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.util.Utils;
import java.util.List;
import java.util.Objects;
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
  public final ExceptionTable exceptionTable;

  public KClass clazz;

  public String getReturnType() {
    return this.descriptor.substring(this.descriptor.indexOf(")") + 1);
  }

  public List<String> getArgs() {
    return Utils.parseMethodDescriptor(this.descriptor);
  }

  public int getArgSlotSize() {
    int count = Utils.parseMethodDescriptor(this.descriptor).stream()
        .map(it -> {
          if (Objects.equals("J", it)) {
            return 2;
          }
          if (Objects.equals("D", it)) {
            return 2;
          }
          return 1;
        })
        .reduce(0, (pre, next) -> pre + next);

    return count;
  }

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

  public boolean isStatic() {
    return (this.accessFlags & 0x0008) != 0;
  }

  public String nativeMethodKey() {
    return String.format("%s_%s_%s", this.clazz.name, name, descriptor);
  }

  public Integer getHandlerPc(Integer pc, String name) {
    for (Exception exception : this.exceptionTable.exceptions) {
      if (exception.clazz == null || Objects.equals(exception.clazz, name)) {
        if (pc >= exception.startPc && pc < exception.endPc) {
          return exception.handlerPc;
        }
      }
    }
    return null;
  }
}
