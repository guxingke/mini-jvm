package com.gxk.jvm.classfile;

import com.gxk.jvm.instruction.*;
import java.io.DataInputStream;
import java.io.IOException;

public abstract class InstructionReader {

  public static Instruction read(int opCode, DataInputStream stream) throws IOException {
    switch (opCode) {
      case 0x0:
        return new NopInst();
      case 0x3:
        return new Iconst0Inst();
      case 0x4:
        return new Iconst1Inst();
      case 0x3c:
        return new Istore1Inst();
      case 0x3d:
        return new Istore2Inst();
      case 0x10:
        return new BiPushInst(stream.readByte());
      case 0xa3:
        return new IfIcmpGtInst(stream.readUnsignedShort());
      case 0x1b:
        return new Iload1Inst();
      case 0x1c:
        return new Iload2Inst();
      case 0x60:
        return new IaddInst();
      case 0x84:
        return new IIncInst(stream.readUnsignedByte(), stream.readUnsignedByte());
      case 0xa7:
        return new Goto1Inst(stream.readUnsignedShort());
      case 0xb1:
        return new IreturnInst();
      default:
        return null;
//        throw new UnsupportedOperationException("unknown op code");
    }
  }
}
