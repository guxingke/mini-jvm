package com.gxk.jvm;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.ClassReader;
import com.gxk.jvm.classfile.CodeAttribute;
import com.gxk.jvm.classfile.CodeFromByte;
import com.gxk.jvm.classfile.Method;
import com.gxk.jvm.classfile.MethodInfo;
import com.gxk.jvm.classfile.attribute.Code;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.rtda.Env;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class VirtualMachine {

  public void run(Args cmd) throws IOException {

    Path path = Paths.get(cmd.classpath, cmd.clazz + ".class");
    ClassFile cf = ClassReader.read(path);

    Method method = cf.getMainMethod();
    if (method == null) {
      throw new IllegalStateException("not found main method");
    }

    MethodInfo info = map(method.getCode());

    new Interpreter().interpret(info, new Env(cf.cpInfo));
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
