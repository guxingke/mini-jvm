package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.UnionSlot;
import com.gxk.jvm.rtda.heap.Field;
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
    UnionSlot us = null;
    if (fieldDescriptor.equals("J") || fieldDescriptor.equals("D")) {
      final Slot low = frame.pop();
      final Slot high = frame.pop();
      us = UnionSlot.of(high, low);
    } else {
      us = UnionSlot.of(frame.pop());
    }

    final KObject self = frame.popRef();
    Field field = self.getField(fieldName, fieldDescriptor);
    field.set(us);
  }

  @Override
  public String format() {
    return "putfield " + clazz + " " + fieldName + " " + fieldDescriptor;
  }
}
