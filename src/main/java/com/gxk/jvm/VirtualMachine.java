package com.gxk.jvm;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.CodeAttribute;
import com.gxk.jvm.classfile.CodeFromByte;
import com.gxk.jvm.classfile.Method;
import com.gxk.jvm.classfile.MethodInfo;
import com.gxk.jvm.classfile.attribute.Code;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.rtda.Env;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class VirtualMachine {

  void run(Args cmd) throws IOException {

    Entry entry = Classpath.parse(cmd.classpath);
    ClassFile cf = entry.findClass(cmd.clazz);

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

    Map<Integer, Instruction> map = new HashMap<>(instructions.length);
    int pc = 0;
    for (Instruction instruction : instructions) {
      map.put(pc, instruction);
      pc += instruction.offset();
    }
    CodeAttribute codeAttribute = new CodeAttribute(new CodeFromByte(map), maxLocals, maxStacks);
    return new MethodInfo(codeAttribute);
  }

}
