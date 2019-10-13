package com.gxk.jvm.classfile;

import com.gxk.jvm.classfile.cp.IntegerCp;
import com.gxk.jvm.classfile.cp.MethodDef;
import com.gxk.jvm.classfile.cp.NameAndType;
import com.gxk.jvm.classfile.cp.StringCp;
import com.gxk.jvm.instruction.*;
import com.gxk.jvm.util.Utils;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class InstructionReader {

  public static Instruction read(int opCode, DataInputStream stream, ConstantPool constantPool) throws IOException {
    switch (opCode) {
      case 0x0:
        return new NopInst();
      case 0x3:
        return new Iconst0Inst();
      case 0x4:
        return new Iconst1Inst();
      case 0x3b:
        return new Istore0Inst();
      case 0x3c:
        return new Istore1Inst();
      case 0x3d:
        return new Istore2Inst();
      case 0x10:
        return new BiPushInst(stream.readByte());
      case 0xa3:
        return new IfIcmpGtInst(stream.readShort());
      case 0x1a:
        return new Iload0Inst();
      case 0x1b:
        return new Iload1Inst();
      case 0x1c:
        return new Iload2Inst();
      case 0x60:
        return new IaddInst();
      case 0x84:
        return new IIncInst(stream.readUnsignedByte(), stream.readUnsignedByte());
      case 0xa7:
        return new Goto1Inst(stream.readShort());
      case 0xac:
        return new IreturnInst();
      case 0xb1:
        return new ReturnInst();
      case 0xb2:
        return new GetstaticInst(stream.readUnsignedShort());
      case 0x12:
        int index = stream.readUnsignedByte();
        ConstantInfo info = constantPool.infos[index - 1];
        switch (info.infoEnum) {
          case CONSTANT_String:
            int stringIndex = ((StringCp) info).stringIndex;
            String string = Utils.getString(constantPool, stringIndex);
            return new LdcInst(null, string);
          case CONSTANT_Integer:
            return new LdcInst(((IntegerCp) info).val, null);
        }
        throw new IllegalStateException();
      case 0xb6:
        return new InvokespecialInst(stream.readUnsignedShort());
      case 0xb8:
        ConstantInfo methodinfo= constantPool.infos[stream.readUnsignedShort() - 1];
        MethodDef methodDef = (MethodDef) methodinfo;
        NameAndType nat = (NameAndType) constantPool.infos[methodDef.nameAndTypeIndex - 1];

        String methodName= Utils.getString(constantPool, nat.getNameIndex());
        return new InvokestaticInst(methodName);
      default:
        return null;
//        throw new UnsupportedOperationException("unknown op code");
    }

  }
}
