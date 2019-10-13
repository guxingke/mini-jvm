package com.gxk.jvm;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.Method;
import com.gxk.jvm.classfile.attribute.Code;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.rtda.heap.KMethod;

import java.io.IOException;

class VirtualMachine {

  void run(Args cmd) throws IOException {

    Entry entry = Classpath.parse(cmd.classpath);
    ClassFile cf = entry.findClass(cmd.clazz);

    Method method = cf.getMainMethod();
    if (method == null) {
      throw new IllegalStateException("not found main method");
    }

    new Interpreter().interpret(map(method));
  }

  private KMethod map(Method cfMethod) {
    Code code = cfMethod.getCode();
    return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, code.getMaxStacks(), code.getMaxLocals(), code.getInstructions());
  }
}
