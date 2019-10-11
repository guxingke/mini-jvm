package com.gxk.jvm.instruction;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.cp.IntegerCp;
import com.gxk.jvm.classfile.cp.StringCp;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.util.Utils;
import jdk.nashorn.internal.runtime.ConsString;

public class LdcInst implements Instruction {
  public final int index;

  public LdcInst(int index) {
    this.index = index;
  }


  @Override
  public void execute(Frame frame) {
    ConstantInfo info = frame.env.constantPool.infos[index - 1];
    switch (info.infoEnum) {
      case CONSTANT_String:
        int stringIndex = ((StringCp) info).stringIndex;
        String string = Utils.getString(frame.env.constantPool, stringIndex);
        frame.operandStack.pushRef(string);
        break;
      case CONSTANT_Integer:
        frame.operandStack.pushInt(((IntegerCp) info).val);
        break;
      default:
        throw new UnsupportedOperationException("ldc");
    }
  }
}
