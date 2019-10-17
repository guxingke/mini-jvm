package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KObject;

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
    if (clazz.equals("java/lang/System")) {
      return;
    }

    KObject obj = ((KObject) frame.operandStack.popRef());
    KField field = obj.getField(fieldName, fieldDescriptor);
    Slot val = field.val;
    if (val.ref != null) {
      frame.operandStack.pushRef(val.ref);
    } else {
      frame.operandStack.pushInt(val.num);
    }
  }
}
