package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KField;
import com.gxk.jvm.rtda.memory.KObject;
import com.gxk.jvm.util.Utils;

public class GetFieldInst implements Instruction {

  public final String clazz;
  public final String fieldName;
  public final String fieldDescriptor;

  @Override
  public int offset() {
    return 3;
  }

  public GetFieldInst(String clazz, String fieldName, String fieldDescriptor) {
    this.clazz = clazz;
    this.fieldName = fieldName;
    this.fieldDescriptor = fieldDescriptor;
  }


  @Override
  public void execute(Frame frame) {
    // hack for java/nio/charset/Charset name Ljava/lang/String;
    if (clazz.equals("java/nio/charset/Charset") && fieldName.equals("name")) {
      KObject obj = Heap.load(frame.popRef());
      Slot[] val = {new Slot(Utils.str2Obj("UTF-8", obj.clazz.classLoader))};
      obj.setField(fieldName, fieldDescriptor, val);

      for (Slot slot : val) {
        frame.pushSlot(slot);
      }
      return;
    }

    KObject obj = Heap.load(frame.popRef());
    KField field = obj.getField(fieldName, fieldDescriptor);
    Slot[] val = field.val();

    for (Slot slot : val) {
      frame.pushSlot(slot);
    }
  }

  @Override
  public String format() {
    return "getfield " + clazz + " " + fieldName + " " + fieldDescriptor;
  }

  @Override
  public String toString() {
    return "GetFieldInst{" +
        "clazz='" + clazz + '\'' +
        ", fieldName='" + fieldName + '\'' +
        ", fieldDescriptor='" + fieldDescriptor + '\'' +
        '}';
  }
}
