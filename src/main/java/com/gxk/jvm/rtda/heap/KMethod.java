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
}
