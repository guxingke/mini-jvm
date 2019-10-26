package com.gxk.jvm.classfile;

import com.gxk.jvm.classfile.cp.DoubleCp;
import com.gxk.jvm.classfile.cp.FloatCp;
import com.gxk.jvm.classfile.cp.IntegerCp;
import com.gxk.jvm.classfile.cp.LongCp;
import com.gxk.jvm.classfile.cp.StringCp;
import com.gxk.jvm.instruction.*;
import com.gxk.jvm.util.Utils;

import java.io.IOException;

public abstract class InstructionReader {

  public static Instruction read(int opCode, MyDataInputStream stream, ConstantPool constantPool) throws IOException {
    switch (opCode) {
      case 0x0:
        return new NopInst();
      case 0x1:
        return new AconstNullInst();
      case 0x2:
        return new IConstM1Inst();
      case 0x3:
        return new IConst0Inst();
      case 0x4:
        return new IConst1Inst();
      case 0x5:
        return new IConst2Inst();
      case 0x6:
        return new IConst3Inst();
      case 0x7:
        return new IConst4Inst();
      case 0x8:
        return new IConst5Inst();
      case 0x9:
        return new Lconst0Inst();
      case 0xa:
        return new Lconst1Inst();
      case 0xb:
        return new FConst2Inst();
      case 0xd:
        return new FConst2Inst();
      case 0xe:
        return new DConst0Inst();
      case 0xf:
        return new DConst1Inst();

      case 0x10:
        return new BiPushInst(stream.readByte());
      case 0x11:
        return new SiPushInst(stream.readShort());
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
        throw new IllegalStateException();
      case 0x13:
        int lwIndex = stream.readUnsignedShort();
        ConstantInfo lwInfo = constantPool.infos[lwIndex - 1];
        switch (lwInfo.infoEnum) {
          case CONSTANT_String:
            int stringIndex = ((StringCp) lwInfo).stringIndex;
            String string = Utils.getString(constantPool, stringIndex);
            return new LdcWInst("Ljava/lang/String", string);
          case CONSTANT_Integer:
            return new LdcWInst("I", ((IntegerCp) lwInfo).val);
          case CONSTANT_Float:
            return new LdcWInst("F", ((FloatCp) lwInfo).val);
          case CONSTANT_Class:
            return new LdcWInst("L", lwInfo);
        }
        throw new IllegalStateException();
      case 0x14:
        int ldwIdx = stream.readUnsignedShort();
        ConstantInfo ldwInfo = constantPool.infos[ldwIdx - 1];
        switch (ldwInfo.infoEnum) {
          case CONSTANT_Double:
            return new Ldc2wInst(null, ((DoubleCp) ldwInfo).val);
          case CONSTANT_Long:
            return new Ldc2wInst(((LongCp) ldwInfo).val, null);
        }
        throw new IllegalStateException(ldwInfo.toString());
      case 0x15:
        return new IloadNInst(stream.readUnsignedByte());
      case 0x16:
        return new LloadInst(stream.readUnsignedByte());
      case 0x17:
        return new FLoadInst(stream.readUnsignedByte());
      case 0x18:
        return new DLoadInst(stream.readUnsignedByte());
      case 0x19:
        return new ALoadInst(stream.readUnsignedByte());
      case 0x1a:
        return new ILoad0Inst();
      case 0x1b:
        return new ILoad1Inst();
      case 0x1c:
        return new ILoad2Inst();
      case 0x1d:
        return new ILoad3Inst();
      case 0x1e:
        return new LLoad0Inst();
      case 0x1f:
        return new LLoad1Inst();

      case 0x20:
        return new LLoad2Inst();
      case 0x21:
        return new LLoad3Inst();
      case 0x22:
        return new FLoad0Inst();
      case 0x23:
        return new FLoad1Inst();
      case 0x24:
        return new FLoad2Inst();
      case 0x25:
        return new FLoad3Inst();
      case 0x26:
        return new DLoad0Inst();
      case 0x27:
        return new DLoad1Inst();
      case 0x28:
        return new DLoad2Inst();
      case 0x29:
        return new DLoad3Inst();
      case 0x2a:
        return new ALoad0Inst();
      case 0x2b:
        return new ALoad1Inst();
      case 0x2c:
        return new ALoad2Inst();
      case 0x2d:
        return new ALoad3Inst();
      case 0x2e:
        return new IALoadInst();
      case 0x2f:
        return new LALoadInst();

      case 0x30:
        return new FALoadInst();
      case 0x31:
        return new DALoadInst();
      case 0x32:
        return new AALoadInst();
      case 0x34:
        return new CAloadInst();
      case 0x35:
        return new SALoadInst();
      case 0x36:
        return new IStoreNInst(stream.readUnsignedByte());
      case 0x37:
        return new LStoreNInst(stream.readUnsignedByte());
      case 0x38:
        return new FStoreNInst(stream.readUnsignedByte());
      case 0x39:
        return new DStoreNInst(stream.readUnsignedByte());
      case 0x3a:
        return new AStoreInst(stream.readUnsignedByte());
      case 0x3b:
        return new IStore0Inst();
      case 0x3c:
        return new IStore1Inst();
      case 0x3d:
        return new IStore2Inst();
      case 0x3e:
        return new IStore3Inst();
      case 0x3f:
        return new LStore0Inst();

      case 0x40:
        return new LStore1Inst();
      case 0x41:
        return new LStore2Inst();
      case 0x42:
        return new LStore3Inst();
      case 0x43:
        return new FStore0Inst();
      case 0x44:
        return new FStore1Inst();
      case 0x45:
        return new FStore2Inst();
      case 0x46:
        return new FStore3Inst();
      case 0x47:
        return new DStore0Inst();
      case 0x48:
        return new DStore1Inst();
      case 0x49:
        return new DStore2Inst();
      case 0x4a:
        return new DStore3Inst();
      case 0x4b:
        return new AStore0Inst();
      case 0x4c:
        return new AStore1Inst();
      case 0x4d:
        return new AStore2Inst();
      case 0x4e:
        return new AStore3Inst();
      case 0x4f:
        return new IAStoreInst();

      case 0x50:
        return new LAStoreInst();
      case 0x51:
        return new FAStoreInst();
      case 0x52:
        return new DAStoreInst();
      case 0x53:
        return new AAStoreInst();
      case 0x54:
        return new BAStoreInst();
      case 0x55:
        return new CAStoreInst();
      case 0x56:
        return new SAStoreInst();
      case 0x57:
        return new PopInst();
      case 0x58:
        return new Pop2Inst();
      case 0x59:
        return new DupInst();
      case 0x5a:
        return new DupX1Inst();
      case 0x5b:
        return new DupX2Inst();
      case 0x5c:
        return new Dup2Inst();
      case 0x5d:
        return new Dup2X1Inst();
      case 0x5e:
        return new Dup2X2Inst();
      case 0x5f:
        return new SwapInst();

      case 0x60:
        return new IAddInst();
      case 0x61:
        return new LAddInst();
      case 0x62:
        return new FAddInst();
      case 0x63:
        return new DAddInst();
      case 0x64:
        return new ISubInst();
      case 0x65:
        return new LSubInst();
      case 0x66:
        return new FSubInst();
      case 0x67:
        return new DSubInst();
      case 0x68:
        return new IMulInst();
      case 0x69:
        return new LMulInst();
      case 0x6a:
        return new FMulInst();
      case 0x6b:
        return new DMulInst();
      case 0x6c:
        return new IDivInst();
      case 0x6d:
        return new LDivInst();
      case 0x6e:
        return new FDivInst();
      case 0x6f:
        return new DDivInst();

      case 0x70:
        return new IRemInst();
      case 0x71:
        return new LRemInst();
      case 0x72:
        return new FRemInst();
      case 0x73:
        return new DRemInst();
      case 0x74:
        return new INegInst();
      case 0x75:
        return new LNegInst();
      case 0x76:
        return new FNegInst();
      case 0x77:
        return new DNegInst();
      case 0x78:
        return new IShlInst();
      case 0x79:
        return new LShlInst();
      case 0x7a:
        return new IShrInst();
      case 0x7b:
        return new LShrInst();
        // TODO .....
      case 0x7e:
        return new IAndInst();
      case 0x7f:
        return new LAndInst();

      case 0x84:
        return new IIncInst(stream.readUnsignedByte(), stream.readUnsignedByte());
      case 0x86:
        return new I2fInst();
      case 0x8b:
        return new F2iInst();

      case 0x92:
        return new I2cInst();
      case 0x9a:
        return new IfNeInst(stream.readShort());
      case 0x9b:
        return new IfLtInst(stream.readShort());
      case 0x9c:
        return new IfGeInst(stream.readShort());
      case 0x9e:
        return new IfLeInst(stream.readShort());
      case 0x9f:
        return new IfICmpEqInst(stream.readShort());

      case 0xa0:
        return new IfICmpNeInst(stream.readShort());
      case 0xa1:
        return new IfICmpLtInst(stream.readShort());
      case 0xa2:
        return new IfICmpGeInst(stream.readShort());
      case 0xa3:
        return new IfICmpGtInst(stream.readShort());
      case 0xa4:
        return new IfICmpLeInst(stream.readShort());
      case 0xad:
        return new LReturnInst();


      case 0x94:
        return new LCmpInst();
      case 0x96:
        return new FCmpGInst();
      case 0x99:
        return new IfEqInst(stream.readShort());

      case 0xa6:
        return new IfACmpNeInst(stream.readShort());
      case 0xa7:
        return new Goto1Inst(stream.readShort());
      case 0xaa:
        int offset = 24;

        int padding = stream.readPadding();
        offset += padding;

        int tsDefault = stream.readInt();
        int tsLow = stream.readInt();
        int tsHigh = stream.readInt();
        int tsOffsetByteLength = (tsHigh - tsLow + 1) * 4;

        byte[] tsBytes = Utils.readNBytes(stream, tsOffsetByteLength);
        offset += tsBytes.length;

        return new TableSwitchInst(offset, tsDefault, tsLow, tsHigh, tsBytes);
      case 0xab:
        int lsOffset = 16;
        int lsPadding = stream.readPadding();
        lsOffset += lsPadding;
        int lsDef = stream.readInt();
        int lsPairsCnt = stream.readInt();
        int lsPairsLen = lsPairsCnt * 2 * 4;
        byte[] lsBytes = Utils.readNBytes(stream, lsPairsLen);
        lsOffset += lsBytes.length;
        return new LookupSwitchInst(lsOffset, lsDef, lsPairsCnt, lsBytes);
      case 0xac:
        return new IReturnInst();

      case 0xb0:
        return new AReturnInst();
      case 0xb1:
        return new ReturnInst();
      case 0xb2:
        int gsIndex = stream.readUnsignedShort();
        return new GetStaticInst(
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
        return new InvokeSpecialInst(
          Utils.getClassNameByMethodDefIdx(constantPool, isIndex),
          Utils.getMethodNameByMethodDefIdx(constantPool, isIndex),
          Utils.getMethodTypeByMethodDefIdx(constantPool, isIndex)
        );

      case 0xb8:
        int mdIdx = stream.readUnsignedShort();
        return new InvokeStaticInst(
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

      case 0xbb:
        return new NewInst(Utils.getClassName(constantPool, stream.readUnsignedShort()));
      case 0xbc:
        return new NewArrayInst(stream.readUnsignedByte());
      case 0xbd:
        return new ANewArrayInst(stream.readUnsignedShort());
      case 0xbe:
        return new ArrayLengthInst();
      case 0xbf:
        return new AThrowInst();

      case 0xc0:
        return new CheckcastInst(Utils.getClassName(constantPool, stream.readUnsignedShort()));
      case 0xc1:
        int ioClazzIdx = stream.readUnsignedShort();
        return new InstanceofInst(Utils.getClassName(constantPool, ioClazzIdx));
      case 0xc2:
        return new MonitorEnterInst();
      case 0xc3:
        return new MonitorExitInst();
      case 0xc6:
        return new IfNullInst(stream.readShort());
      case 0xc7:
        return new IfNonNullInst(stream.readShort());

      default:
        return null;
//        throw new UnsupportedOperationException("unknown op code");
    }
  }
}
