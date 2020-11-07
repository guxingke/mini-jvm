package com.gxk.jvm.instruction;

import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.rtda.*;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Class;
import com.gxk.jvm.rtda.heap.Method;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.rtda.heap.NativeMethod;

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
    Class aClass = Heap.findClass(clazz);

    if (aClass == null) {
      ClassLoader loader = frame.method.clazz.classLoader;
      aClass = loader.loadClass(clazz);
    }

    if (aClass == null) {
      throw new IllegalStateException(ClassNotFoundException.class.getName());
    }

    if (!aClass.getStat()) {
      // interfaceInit
      Method cinit = aClass.getClinitMethod();
      if (cinit == null) {
        aClass.setStat(2);
        frame.nextPc = frame.getPc();
        return;
      }

      String clNm = cinit.nativeMethodKey();
      NativeMethod clm = Heap.findMethod(clNm);
      if (clm != null) {
        clm.invoke(frame);
      } else {
        Frame newFrame = new Frame(cinit);
        aClass.setStat(1);
        Class finalClass = aClass;
        newFrame.setOnPop(() -> finalClass.setStat(2));
        frame.thread.pushFrame(newFrame);

        frame.nextPc = frame.getPc();
        return;
      }
    }

    KObject obj = aClass.newObject();
    frame.pushRef(obj);
  }

  @Override
  public String format() {
    return "new " + clazz;
  }
}

