package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import lombok.Data;

@Data
public class ANewArrayInst implements Instruction{

  public final String className;

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    KClass kClass = Heap.findClass(className);
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

    Integer count = frame.operandStack.popInt();
    KClass arrClass = new KClass("[" + kClass.getName(), kClass.classLoader);
    KObject[] objs = new KObject[count];
    KArray kArray = new KArray(arrClass, objs);
    frame.operandStack.pushRef(kArray);
  }
}