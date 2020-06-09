package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KMethod;

import java.util.ArrayList;
import java.util.List;

public class GetStaticInst implements Instruction {
  public final String clazz;
  public final String fieldName;
  public final String fieldDescriptor;

  @Override
  public int offset() {
    return 3;
  }

  public GetStaticInst(String clazz, String fieldName, String fieldDescriptor) {
    this.clazz = clazz;
    this.fieldName = fieldName;
    this.fieldDescriptor = fieldDescriptor;
  }


  @Override
  public void execute(Frame frame) {
    KClass kClass = Heap.findClass(clazz);
    if (kClass == null) {
      kClass = frame.method.clazz.classLoader.loadClass(clazz);
    }

    if (!kClass.isStaticInit()) {
      KMethod cinit = kClass.getClinitMethod();
      if (cinit == null) {
        throw new IllegalStateException();
      }

      Frame newFrame = new Frame(cinit, frame.thread);
      kClass.setStaticInit(1);
      KClass finalKClass = kClass;
      newFrame.setOnPop(() -> finalKClass.setStaticInit(2));
      frame.thread.pushFrame(newFrame);

      frame.nextPc = frame.thread.getPc();
      return;
    }

    KField field = kClass.getField(fieldName, fieldDescriptor);
    if (field == null) {
      // interface
      if (kClass.interfaceNames.isEmpty()) {
        throw new IllegalStateException();
      }

      // already load interface
      if (!kClass.getInterfaces().isEmpty()) {
        for (KClass intClass : kClass.getInterfaces()) {
          field = intClass.getField(fieldName, fieldDescriptor);
          if (field != null) {
            break;
          }
        }
      } else {
        List<KClass> interfaces = new ArrayList<>();
        for (String interfaceName : kClass.interfaceNames) {
          KClass tmp = Heap.findClass(interfaceName);
          if (tmp == null) {
            tmp = frame.method.clazz.classLoader.loadClass(interfaceName);
          }

          interfaces.add(tmp);

          if (!tmp.isStaticInit()) {
            KMethod cinit = tmp.getClinitMethod();
            if (cinit == null) {
              throw new IllegalStateException();
            }

            Frame newFrame = new Frame(cinit, frame.thread);
            tmp.setStaticInit(1);
            KClass finalKClass = tmp;
            newFrame.setOnPop(() -> finalKClass.setStaticInit(2));
            frame.thread.pushFrame(newFrame);
            frame.nextPc = frame.thread.getPc();
          }
        }
        kClass.setInterfaces(interfaces);
        return;
      }
    }

    if (field == null) {
      throw new IllegalStateException();
    }

    if (field.val == null) {
      throw new IllegalStateException();
    }

    Slot[] val = field.val;
    for (Slot slot : val) {
      frame.pushSlot(slot);
    }
  }


  @Override
  public String format() {
    return "getstatic " + clazz + " " + fieldName + " " + fieldDescriptor;
  }

  @Override
  public String toString() {
    return "GetStaticInst{" +
        "clazz='" + clazz + '\'' +
        ", fieldName='" + fieldName + '\'' +
        ", fieldDescriptor='" + fieldDescriptor + '\'' +
        '}';
  }
}
