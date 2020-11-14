package com.gxk.jvm.instruction.references;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Class;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Instance;
import com.gxk.jvm.rtda.heap.InstanceArray;
import com.gxk.jvm.util.Utils;

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
    Class aClass = frame.method.clazz.classLoader.loadClass(className);
    Utils.clinit(aClass);

    int count = frame.popInt();
    String name = "[L" + aClass.name + ";";

    Class clazz = Heap.findClass(name);
    if (clazz == null) {
      clazz = new Class(1, name, aClass.classLoader, null);
      clazz.setSuperClass(Heap.findClass("java/lang/Object"));
      clazz.setStat(2);
      Heap.registerClass(name, clazz);
    }
    Instance[] objs = new Instance[count];
    InstanceArray instanceArray = new InstanceArray(clazz, objs);
    frame.pushRef(instanceArray);
  }

  @Override
  public String format() {
    return "anewarray";
  }
}