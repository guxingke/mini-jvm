package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Class;
import com.gxk.jvm.rtda.heap.Field;
import com.gxk.jvm.rtda.heap.Method;

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
    Class aClass = Heap.findClass(clazz);
    if (aClass == null) {
      aClass = frame.method.clazz.classLoader.loadClass(clazz);
    }

    if (!aClass.getStat()) {
      Method cinit = aClass.getMethod("<clinit>", "()V");
      if (cinit == null) {
        throw new IllegalStateException();
      }

      Frame newFrame = new Frame(cinit);
      aClass.setStat(1);
      Class finalClass = aClass;
      newFrame.setOnPop(() -> finalClass.setStat(2));
      frame.thread.pushFrame(newFrame);

      frame.nextPc = frame.getPc();
      return;
    }

    Field field = aClass.getField(fieldName, fieldDescriptor);
    if (field == null) {
      // interface
      if (aClass.interfaceNames.isEmpty()) {
        throw new IllegalStateException();
      }

      // already load interface
      if (!aClass.getInterfaces().isEmpty()) {
        for (Class intClass : aClass.getInterfaces()) {
          field = intClass.getField(fieldName, fieldDescriptor);
          if (field != null) {
            break;
          }
        }
      } else {
        List<Class> interfaces = new ArrayList<>();
        for (String interfaceName : aClass.interfaceNames) {
          Class tmp = Heap.findClass(interfaceName);
          if (tmp == null) {
            tmp = frame.method.clazz.classLoader.loadClass(interfaceName);
          }

          interfaces.add(tmp);

          if (!tmp.getStat()) {
            Method cinit = tmp.getClinitMethod();
            if (cinit == null) {
              throw new IllegalStateException();
            }

            Frame newFrame = new Frame(cinit);
            tmp.setStat(1);
            Class finalClass = tmp;
            newFrame.setOnPop(() -> finalClass.setStat(2));
            frame.thread.pushFrame(newFrame);
            frame.nextPc = frame.getPc();
          }
        }
        aClass.setInterfaces(interfaces);
        return;
      }
    }

    if (field == null) {
      throw new IllegalStateException();
    }

    if (field.val == null) {
      throw new IllegalStateException();
    }

    field.get(frame);
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
