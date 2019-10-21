package com.gxk.jvm.rtda.heap;

import com.gxk.jvm.instruction.Instruction;
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
}
