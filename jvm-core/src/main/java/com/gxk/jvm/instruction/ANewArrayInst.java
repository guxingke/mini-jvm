package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.MetaSpace;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
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
    KClass kClass = MetaSpace.findClass(className);
    if (kClass == null) {
      kClass = frame.method.clazz.classLoader.loadClass(className);
    }
    if (!kClass.isStaticInit()) {
      KMethod method = kClass.getClinitMethod();
      if (method != null) {
        Frame newFrame = new Frame(method, frame.thread);
        kClass.setStaticInit(1);
        KClass finalKClass = kClass;
        newFrame.setOnPop(() -> finalKClass.setStaticInit(2));
        frame.thread.pushFrame(frame);
        return;
      }
      kClass.setStaticInit(2);
    }

    Integer count = frame.popInt();
    String name = "[L" + kClass.name + ";";

    KClass clazz = MetaSpace.findClass(name);
    if (clazz == null) {
      clazz = new KClass(1, name, kClass.classLoader, null);
      clazz.setSuperClass(MetaSpace.findClass("java/lang/Object"));
      clazz.setStaticInit(2);
      MetaSpace.registerClass(name, clazz);
    }
    KObject[] objs = new KObject[count];
    KArray kArray = new KArray(clazz, objs, objs.length);
    frame.pushRef(kArray);
  }

  @Override
  public String format() {
    return "anewarray";
  }
}