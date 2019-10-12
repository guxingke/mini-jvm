package com.gxk.jvm.instruction;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.ClassReader;
import com.gxk.jvm.classfile.CodeAttribute;
import com.gxk.jvm.classfile.CodeFromByte;
import com.gxk.jvm.classfile.Method;
import com.gxk.jvm.classfile.MethodInfo;
import com.gxk.jvm.classfile.attribute.Code;
import com.gxk.jvm.rtda.Env;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Thread;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class InvokestaticInst implements Instruction {

  public final int methodRefIndex;

  public InvokestaticInst(int methodRefIndex) {
    this.methodRefIndex = methodRefIndex;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Frame frame2 = sum10Frame(frame.thread);
    frame.thread.pushFrame(frame2);
  }

  // TODO temp
  private Frame sum10Frame(Thread thread) {
    ClassFile cf = null;
    try {
      cf = ClassReader.read(Paths.get("example/Loop4.class"));
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    Method sum10= cf.methods.methods[2];

    com.gxk.jvm.classfile.attribute.Code attribute = (com.gxk.jvm.classfile.attribute.Code) sum10.attributes.attributes[0];
    MethodInfo method = map(attribute);
    Env env = new Env(cf.cpInfo);

    Frame frame = new Frame(method.code.maxLocals, method.code.maxStacks, method.code.code, thread, env);

    return frame;
  }

  private MethodInfo map(Code attribute) {
    int maxStacks = attribute.getMaxStacks();
    int maxLocals = attribute.getMaxLocals();
    Instruction[] instructions = attribute.getInstructions();

    Map<Integer, Instruction> map = new HashMap<>();
    int pc = 0;
    for (Instruction instruction : instructions) {
      map.put(pc, instruction);
      pc += instruction.offset();
    }
    CodeAttribute codeAttribute = new CodeAttribute(new CodeFromByte(map), maxLocals, maxStacks);
    return new MethodInfo(codeAttribute);
  }
}
