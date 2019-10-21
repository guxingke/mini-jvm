package com.gxk.jvm.classfile;

import com.gxk.jvm.classfile.cp.IntegerCp;
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
      case 0xbe:
        return new ArrayLengthInst();
      case 0x3:
        return new Iconst0Inst();
      case 0x4:
        return new Iconst1Inst();
      case 0x5:
        return new Iconst2Inst();
      case 0x6:
        return new Iconst3Inst();
      case 0x32:
        return new AAloadInst();
      case 0x3b:
        return new Istore0Inst();
      case 0x3c:
        return new Istore1Inst();
      case 0x3d:
        return new Istore2Inst();
      case 0x3e:
        return new Istore3Inst();
      case 0x36:
        return new IstoreNInst(stream.readUnsignedByte());
      case 0x4c:
        return new Astore1Inst();
      case 0x4d:
        return new Astore2Inst();
      case 0x4e:
        return new Astore3Inst();
      case 0x59:
        return new DupInst();
      case 0x2a:
        return new Aload0Inst();
      case 0x2b:
        return new Aload1Inst();
      case 0x2c:
        return new Aload2Inst();
      case 0x2d:
        return new Aload3Inst();
      case 0x10:
        return new BiPushInst(stream.readByte());
      case 0x9a:
        return new IfneInst(stream.readShort());
      case 0xa2:
        return new IfIcmpGeInst(stream.readShort());
      case 0xa3:
        return new IfIcmpGtInst(stream.readShort());
      case 0x9f:
        return new IfIcmpEqInst(stream.readShort());
      case 0xa0:
        return new IfIcmpNeInst(stream.readShort());
      case 0x1a:
        return new Iload0Inst();
      case 0x1b:
        return new Iload1Inst();
      case 0x1c:
        return new Iload2Inst();
      case 0x1d:
        return new Iload3Inst();
      case 0x15:
        return new IloadNInst(stream.readUnsignedByte());
      case 0x60:
        return new IaddInst();
      case 0x64:
        return new ISubInst();
      case 0x84:
        return new IIncInst(stream.readUnsignedByte(), stream.readUnsignedByte());
      case 0xa7:
        return new Goto1Inst(stream.readShort());
      case 0xac:
        return new IreturnInst();
      case 0xb1:
        return new ReturnInst();
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
      case 0xb2:
        int gsIndex = stream.readUnsignedShort();
        return new GetstaticInst(
          Utils.getClassNameByFieldDefIdx(constantPool, gsIndex),
          Utils.getMethodNameByFieldDefIdx(constantPool, gsIndex),
          Utils.getMethodTypeByFieldDefIdx(constantPool, gsIndex)
        );
      case 0xb3:
        int psIndex = stream.readUnsignedShort();
        return new PutStaticInst(
          Utils.getClassNameByFieldDefIdx(constantPool, psIndex),
          Utils.getMethodNameByFieldDefIdx(constantPool, psIndex),
          Utils.getMethodTypeByFieldDefIdx(constantPool, psIndex)
        );
      case 0xb4:
        int gfIndex = stream.readUnsignedShort();
        return new GetFieldInst(
          Utils.getClassNameByFieldDefIdx(constantPool, gfIndex),
          Utils.getMethodNameByFieldDefIdx(constantPool, gfIndex),
          Utils.getMethodTypeByFieldDefIdx(constantPool, gfIndex)
        );
      case 0xb5:
        int pfIndex = stream.readUnsignedShort();
        return new PutFieldInst(
          Utils.getClassNameByFieldDefIdx(constantPool, pfIndex),
          Utils.getMethodNameByFieldDefIdx(constantPool, pfIndex),
          Utils.getMethodTypeByFieldDefIdx(constantPool, pfIndex)
        );
      case 0xb6:
        int ivIndex = stream.readUnsignedShort();
        return new InvokeVirtualInst(
          Utils.getClassNameByMethodDefIdx(constantPool, ivIndex),
          Utils.getMethodNameByMethodDefIdx(constantPool, ivIndex),
          Utils.getMethodTypeByMethodDefIdx(constantPool, ivIndex)
        );
      case 0xb7:
        int isIndex = stream.readUnsignedShort();
        return new InvokespecialInst(
          Utils.getClassNameByMethodDefIdx(constantPool, isIndex),
          Utils.getMethodNameByMethodDefIdx(constantPool, isIndex),
          Utils.getMethodTypeByMethodDefIdx(constantPool, isIndex)
        );

      case 0xb8:
        int mdIdx = stream.readUnsignedShort();
        return new InvokestaticInst(
          Utils.getClassNameByMethodDefIdx(constantPool, mdIdx),
          Utils.getMethodNameByMethodDefIdx(constantPool, mdIdx),
          Utils.getMethodTypeByMethodDefIdx(constantPool, mdIdx)
        );
      case 0xbb:
        return new NewInst(Utils.getClassName(constantPool, stream.readUnsignedShort()));
      default:
        return null;
//        throw new UnsupportedOperationException("unknown op code");
    }
  }
}
