package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.UnionSlot;
import com.gxk.jvm.rtda.heap.KObject;

public class PutFieldInst implements Instruction {
  public final String clazz;
  public final String fieldName;
  public final String fieldDescriptor;

  @Override
  public int offset() {
    return 3;
  }

  public PutFieldInst(String clazz, String fieldName, String fieldDescriptor) {
    this.clazz = clazz;
    this.fieldName = fieldName;
    this.fieldDescriptor = fieldDescriptor;
  }

  @Override
  public void execute(Frame frame) {
    if (fieldDescriptor.equals("J") || fieldDescriptor.equals("D")) {
      Slot v2 = frame.popSlot();
      Slot v1 = frame.popSlot();
      KObject obj = (KObject) frame.popRef();
      obj.setField(fieldName, fieldDescriptor, UnionSlot.of(v1, v2));
      return;
    }

    Slot v = frame.popSlot();
    frame.popRef().setField(fieldName, fieldDescriptor, UnionSlot.of(v));
  }

  @Override
  public String format() {
    return "putfield " + clazz + " " + fieldName + " " + fieldDescriptor;
  }
}
