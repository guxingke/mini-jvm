package com.gxk.jvm;

import com.gxk.jvm.classloader.Classloader;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;

class VirtualMachine {

  void run(Args cmd) {

    Entry entry = Classpath.parse(cmd.classpath);

    Classloader.loadClass(cmd.clazz, entry);

    KClass clazz = Heap.findClass(cmd.clazz);
    KMethod method = clazz.getMainMethod();
    if (method == null) {
      throw new IllegalStateException("not found main method");
    }

    new Interpreter().interpret(method, cmd.args);
  }

}
