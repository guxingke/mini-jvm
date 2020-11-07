package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.Class;
import com.gxk.jvm.rtda.heap.Method;
import com.gxk.jvm.rtda.heap.KObject;

public class ANewArrayInst implements Instruction {

  public final String className;

  public ANewArrayInst(String className) {
    this.className = className;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Class aClass = Heap.findClass(className);
    if (aClass == null) {
      aClass = frame.method.clazz.classLoader.loadClass(className);
    }
    if (!aClass.getStat()) {
      Method method = aClass.getMethod("<clinit>", "V()");
      if (method != null) {
        Frame newFrame = new Frame(method);
        aClass.setStat(1);
        Class finalClass = aClass;
        newFrame.setOnPop(() -> finalClass.setStat(2));
        frame.thread.pushFrame(frame);
        return;
      }
      aClass.setStat(2);
    }

    Integer count = frame.popInt();
    String name = "[L" + aClass.name + ";";

    Class clazz = Heap.findClass(name);
    if (clazz == null) {
      clazz = new Class(1, name, aClass.classLoader, null);
      clazz.setSuperClass(Heap.findClass("java/lang/Object"));
      clazz.setStat(2);
      Heap.registerClass(name, clazz);
    }
    KObject[] objs = new KObject[count];
    KArray kArray = new KArray(clazz, objs);
    frame.pushRef(kArray);
  }

  @Override
  public String format() {
    return "anewarray";
  }
}