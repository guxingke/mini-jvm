package com.gxk.jvm.instruction;

import com.gxk.jvm.classloader.Classloader;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.rtda.*;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;

public class NewInst implements Instruction {

  public final String clazz;

  public NewInst(String clazz) {
    this.clazz = clazz;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    KClass kClass = Heap.findClass(clazz);

    if (kClass == null) {
      Entry entry = frame.method.clazz.getDefaultEntry();
      Classloader.loadClass(clazz, entry);
      kClass = Heap.findClass(clazz);
    }

    if (kClass == null) {
      throw new IllegalStateException(ClassNotFoundException.class.getName());
    }

    if (!kClass.isStaticInit()) {
      // init
      KMethod cinit = kClass.getMethod("<clinit>", "()V");
      if (cinit == null) {
        kClass.setStaticInit(true);
        frame.nextPc = frame.thread.getPc();
        return;
      }

      Frame newFrame = new Frame(cinit, frame.thread);
      KClass finalKClass = kClass;
      newFrame.setOnPop(() -> finalKClass.setStaticInit(true));
      frame.thread.pushFrame(newFrame);

      frame.nextPc = frame.thread.getPc();
      return;
    }

    KObject obj = kClass.newObject();
    frame.operandStack.pushRef(obj);
  }
}

