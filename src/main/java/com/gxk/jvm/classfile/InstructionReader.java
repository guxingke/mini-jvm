package com.gxk.jvm.classfile;

import com.gxk.jvm.classfile.cp.DoubleCp;
import com.gxk.jvm.classfile.cp.FloatCp;
import com.gxk.jvm.classfile.cp.IntegerCp;
import com.gxk.jvm.classfile.cp.LongCp;
import com.gxk.jvm.classfile.cp.StringCp;
import com.gxk.jvm.instruction.*;
import com.gxk.jvm.util.Utils;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class InstructionReader {

  public static Instruction read(int opCode, MyByteArrayInputStream is, ConstantPool constantPool) throws IOException {
    DataInputStream stream = new DataInputStream(is);
    switch (opCode) {
      case 0x0:
        return new NopInst();
      case 0x1:
        return new AconstNullInst();
      case 0x9:
        return new Lconst0Inst();
      case 0xa:
        return new Lconst1Inst();
      case 0x16:
        return new LloadInst(stream.readUnsignedByte());
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
      case 0x7:
        return new Iconst4Inst();
      case 0x8:
        return new Iconst5Inst();
      case 0xd:
        return new Fconst2Inst();
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
      case 0x26:
        return new Dload0Inst();
      case 0xc2:
        return new MonitorEnterInst();
      case 0xc3:
        return new MonitorExitInst();
      case 0x4b:
        return new Astore0Inst();
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
      case 0x11:
        return new SiPushInst(stream.readShort());
      case 0x1f:
        return new Lload1Inst();
      case 0x9a:
        return new IfneInst(stream.readShort());
      case 0x9b:
        return new IfltInst(stream.readShort());
      case 0x9c:
        return new IfGeInst(stream.readShort());
      case 0xa1:
        return new IfIcmpLtInst(stream.readShort());
      case 0xa2:
        return new IfIcmpGeInst(stream.readShort());
      case 0xa3:
        return new IfIcmpGtInst(stream.readShort());
      case 0xa4:
        return new IfIcmpLeInst(stream.readShort());
      case 0x9e:
        return new IfleInst(stream.readShort());
      case 0x9f:
        return new IfIcmpEqInst(stream.readShort());
      case 0xa0:
        return new IfIcmpNeInst(stream.readShort());
      case 0xc6:
        return new IfNullInst(stream.readShort());
      case 0xc7:
        return new IfNonNullInst(stream.readShort());
      case 0x94:
        return new LcmpInst();
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
      case 0x99:
        return new IfeqInst(stream.readShort());
      case 0x45:
        return new Fstore2Inst();
      case 0x57:
        return new PopInst();
      case 0xa7:
        return new Goto1Inst(stream.readShort());
      case 0xac:
        return new IreturnInst();
      case 0xb1:
        return new ReturnInst();
      case 0xad:
        return new LreturnInst();
      case 0x12:
        int index = stream.readUnsignedByte();
        ConstantInfo info = constantPool.infos[index - 1];
        switch (info.infoEnum) {
          case CONSTANT_String:
            int stringIndex = ((StringCp) info).stringIndex;
            String string = Utils.getString(constantPool, stringIndex);
            return new LdcInst("Ljava/lang/String", string);
          case CONSTANT_Integer:
            return new LdcInst("I", ((IntegerCp) info).val);
          case CONSTANT_Float:
            return new LdcInst("F", ((FloatCp) info).val);
          case CONSTANT_Class:
            return new LdcInst("L", info);
        }
        throw new IllegalStateException(info.toString());
      case 0x14:
        int ldwIdx= stream.readUnsignedShort();
        ConstantInfo ldwInfo= constantPool.infos[ldwIdx- 1];
        switch (ldwInfo.infoEnum) {
          case CONSTANT_Double:
            return new Ldc2wInst(null, ((DoubleCp) ldwInfo).val);
          case CONSTANT_Long:
            return new Ldc2wInst(((LongCp) ldwInfo).val, null);
        }
        throw new IllegalStateException(ldwInfo.toString());
      case 0x7e:
        return new IAndInst();
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
      case 0xb9:
        int iiIdx = stream.readUnsignedShort();
        return new InvokeInterfaceInst(
            Utils.getClassNameByIMethodDefIdx(constantPool, iiIdx),
            Utils.getMethodNameByIMethodDefIdx(constantPool, iiIdx),
            Utils.getMethodTypeByIMethodDefIdx(constantPool, iiIdx),
            stream.readUnsignedByte(),
            stream.readUnsignedByte()
        );
      case 0xc0:
        return new CheckcastInst(Utils.getClassName(constantPool, stream.readUnsignedShort()));
      case 0xb0:
        return new AreturnInst();
      case 0xf:
        return new Dconst1Inst();
      case 0xa6:
        return new IfAcmpNeInst(stream.readShort());
      case 0xbb:
        return new NewInst(Utils.getClassName(constantPool, stream.readUnsignedShort()));
      case 0xbf:
        return new AThrowInst();
      case 0x61:
        return new LaddInst();
      case 0x40:
        return new Lstore1Inst();
      case 0x41:
        return new Lstore2Inst();
      case 0x20:
        return new Lload2Inst();
      case 0x75:
        return new LnegInst();
      case 0xbc:
        return new NewArrayInst(stream.readUnsignedByte());
      case 0x34:
        return new CAloadInst();
      case 0x3a:
        return new AstoreInst(stream.readUnsignedByte());
      case 0x19:
        return new AstoreInst(stream.readUnsignedByte());
      case 0x68:
        return new IMulInst();
      case 0xaa:
        int offset = 24;
        while (is.getPosition() % 4 != 0) {
          is.skip(1L);
          offset++;
        }
        int tsDefault = stream.readInt();
        int tsLow = stream.readInt();
        int tsHigh = stream.readInt();
        int tsOffsetByteLength = (tsHigh - tsLow + 1) * 4;
        byte[] tsBytes = new byte[tsOffsetByteLength];
        stream.read(tsBytes);
        offset += tsBytes.length;

        return new TableSwitchInst(offset, tsDefault, tsLow, tsHigh, tsBytes);
      case 0xab:
        int lsOffset = 16;
        while (is.getPosition() % 4 != 0) {
          is.skip(1L);
          lsOffset++;
        }
        int lsDef = stream.readInt();
        int lsPairsCnt = stream.readInt();
        int lsPairsLen = lsPairsCnt * 2 * 4;
        byte[] lsBytes = new byte[lsPairsLen];
        stream.read(lsBytes);
        lsOffset += lsBytes.length;
        return new LookupSwitchInst(lsOffset, lsDef, lsPairsCnt, lsBytes);
      case 0x78:
        return new IShlInst();
      case 0x7a:
        return new IShrInst();
      case 0x92:
        return new I2cInst();
      case 0x55:
        return new CAStoreInst();
      case 0xc1:
        int ioClazzIdx = stream.readUnsignedShort();
        return new InstanceofInst(Utils.getClassName(constantPool, ioClazzIdx));
      case 0x24:
        return new FLoad2Inst();
      case 0xb:
        return new Fconst0Inst();
      case 0xc:
        return new Fconst1Inst();
      case 0x96:
        return new FCmpGInst();
      case 0xbd:
        return new ANewArrayInst(stream.readUnsignedShort());
      case 0x86:
        return new I2fInst();
      case 0x6a:
        return new FMulInst();
      case 0x8b:
        return new F2iInst();
      case 0x70:
        return new IRemInst();
      case 0x53:
        return new AAStoreInst();
      case 0x2:
        return new IconstM1Inst();
      case 0x76:
        return new FNegInst();
      case 0x6e:
        return new FDivInst();
      case 0x5a:
        return new DupX1Inst();
      case 0x6c:
        return new IDivInst();
      default:
        return null;
//        throw new UnsupportedOperationException("unknown op code");
    }
  }
}
