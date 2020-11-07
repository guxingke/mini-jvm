package com.gxk.jvm.rtda.heap;

import com.gxk.jvm.classfile.Exception;
import com.gxk.jvm.classfile.ExceptionTable;
import com.gxk.jvm.classfile.attribute.LineNumberTable;
import com.gxk.jvm.classfile.attribute.LineNumberTable.Line;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.util.Utils;
import java.util.List;
import java.util.Objects;

import java.util.Map;

public class Method {

  public final int accessFlags;
  public final String name;
  public final String descriptor;

  public final int maxStacks;
  public final int maxLocals;
  public final Map<Integer, Instruction> instructionMap;
  public final ExceptionTable exceptionTable;
  public final LineNumberTable lineNumberTable;

  public Class clazz;

  public Method(int accessFlags, String name, String descriptor, int maxStacks, int maxLocals,
      Map<Integer, Instruction> instructionMap, ExceptionTable exceptionTable,
      LineNumberTable lineNumberTable) {
    this.accessFlags = accessFlags;
    this.name = name;
    this.descriptor = descriptor;
    this.maxStacks = maxStacks;
    this.maxLocals = maxLocals;
    this.instructionMap = instructionMap;
    this.exceptionTable = exceptionTable;
    this.lineNumberTable = lineNumberTable;
  }

  public String getReturnType() {
    return this.descriptor.substring(this.descriptor.indexOf(")") + 1);
  }

  public List<String> getArgs() {
    return Utils.parseMethodDescriptor(this.descriptor);
  }

  public int getArgSlotSize() {
    int cnt = 0;
    for (String it : Utils.parseMethodDescriptor(this.descriptor)) {
      if (Objects.equals("J", it)) {
        cnt += 2;
        continue;
      }
      if (Objects.equals("D", it)) {
        cnt += 2;
        continue;
      }
      cnt++;
    }
    if (!Utils.isStatic(this.accessFlags)) {
      cnt++;
    }
    return cnt;
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
        ", clazz=" + clazz.name +
        '}';
  }

  public boolean isNative() {
    return (this.accessFlags & 0x0100) != 0;
  }

  public boolean isStatic() {
    return (this.accessFlags & 0x0008) != 0;
  }

  public String nativeMethodKey() {
    return Utils.genNativeMethodKey(this);
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

  public int getLine(int pc) {
    int ret = 0;
    for (Line line : this.lineNumberTable.lines) {
      if (line.startPc <= pc) {
        ret = line.lineNumber;
      } else {
        break;
      }
    }
    return ret;
  }

  public String getKey() {
    return Utils.genNativeMethodKey(this);
  }
}
