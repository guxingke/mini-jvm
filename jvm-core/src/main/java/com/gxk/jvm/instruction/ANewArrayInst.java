package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.KClass;
import com.gxk.jvm.rtda.memory.KMethod;

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
    KClass kClass = MethodArea.findClass(className);
    if (kClass == null) {
      kClass = frame.method.clazz.classLoader.loadClass(className);
    }
    if (!kClass.isStaticInit()) {
      KMethod method = kClass.getMethod("<clinit>", "V()");
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

    KClass clazz = MethodArea.findClass(name);
    if (clazz == null) {
      clazz = new KClass(1, name, kClass.classLoader, null);
      clazz.setSuperClass(MethodArea.findClass("java/lang/Object"));
      clazz.setStaticInit(2);
      MethodArea.registerClass(name, clazz);
    }
    Long[] objs = new Long[count];
    Long kArray = KArray.newArray(clazz, objs);
    frame.pushRef(kArray);
  }

  @Override
  public String format() {
    return "anewarray";
  }
}