package com.gxk.jvm.classfile;

import com.gxk.jvm.classfile.cp.ClassCp;
import com.gxk.jvm.classfile.cp.FieldDef;
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
      case 0xb2:
        int gsIndex = stream.readUnsignedShort();
        ConstantInfo gsInfo = constantPool.infos[gsIndex - 1];
        FieldDef fieldDef = (FieldDef) gsInfo;
        int gsClassIndex = fieldDef.getClassIndex();
        int gsClassNameIndex = ((ClassCp) constantPool.infos[gsClassIndex - 1]).getNameIndex();
        int gsNTIdx = fieldDef.getNameAndTypeIndex();
        NameAndType gsNT = (NameAndType) constantPool.infos[gsNTIdx - 1];
        return new GetstaticInst(Utils.getString(constantPool, gsClassNameIndex),
            Utils.getString(constantPool, gsNT.getNameIndex()),
            Utils.getString(constantPool, gsNT.getDescriptionIndex()));
      case 0xb3:
        int psIndex = stream.readUnsignedShort();
        ConstantInfo psInfo = constantPool.infos[psIndex - 1];
        FieldDef psFieldDef = (FieldDef) psInfo;
        int psClassIndex = psFieldDef.getClassIndex();
        int psClassNameIndex = ((ClassCp) constantPool.infos[psClassIndex - 1]).getNameIndex();
        int psNTIdx = psFieldDef.getNameAndTypeIndex();
        NameAndType psNT = (NameAndType) constantPool.infos[psNTIdx - 1];
        return new PutStaticInst(Utils.getString(constantPool, psClassNameIndex),
            Utils.getString(constantPool, psNT.getNameIndex()),
            Utils.getString(constantPool, psNT.getDescriptionIndex()));
      case 0xb4:
        int gfIndex = stream.readUnsignedShort();
        ConstantInfo gfInfo = constantPool.infos[gfIndex - 1];
        FieldDef gfFieldDef = (FieldDef) gfInfo;
        int gfClassIndex = gfFieldDef.getClassIndex();
        int gfClassNameIndex = ((ClassCp) constantPool.infos[gfClassIndex - 1]).getNameIndex();
        int gfNTIdx = gfFieldDef.getNameAndTypeIndex();
        NameAndType gfNT = (NameAndType) constantPool.infos[gfNTIdx - 1];
        return new GetFieldInst(Utils.getString(constantPool, gfClassNameIndex),
            Utils.getString(constantPool, gfNT.getNameIndex()),
            Utils.getString(constantPool, gfNT.getDescriptionIndex()));
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
      case 0xb5:
        int pfIndex = stream.readUnsignedShort();
        ConstantInfo pfInfo = constantPool.infos[pfIndex - 1];
        FieldDef pfFieldDef = (FieldDef) pfInfo;
        int pfClassIndex = pfFieldDef.getClassIndex();
        int pfClassNameIndex = ((ClassCp) constantPool.infos[pfClassIndex - 1]).getNameIndex();
        int pfNTIdx = pfFieldDef.getNameAndTypeIndex();
        NameAndType pfNT = (NameAndType) constantPool.infos[pfNTIdx - 1];
        return new PutFieldInst(Utils.getString(constantPool, pfClassNameIndex),
            Utils.getString(constantPool, pfNT.getNameIndex()),
            Utils.getString(constantPool, pfNT.getDescriptionIndex()));
      case 0xb6:
        int ivIndex = stream.readUnsignedShort();
        ConstantInfo ivInfo = constantPool.infos[ivIndex - 1];
        MethodDef ivFieldDef = (MethodDef) ivInfo;
        int ivClassIndex = ivFieldDef.getClassIndex();
        int ivClassNameIndex = ((ClassCp) constantPool.infos[ivClassIndex - 1]).getNameIndex();
        int ivNTIdx = ivFieldDef.getNameAndTypeIndex();
        NameAndType ivNT = (NameAndType) constantPool.infos[ivNTIdx - 1];
        return new InvokeVirtualInst(Utils.getString(constantPool, ivClassNameIndex),
            Utils.getString(constantPool, ivNT.getNameIndex()),
            Utils.getString(constantPool, ivNT.getDescriptionIndex()));
      case 0xb7:
        int isIndex = stream.readUnsignedShort();
        ConstantInfo isInfo = constantPool.infos[isIndex - 1];
        MethodDef isFieldDef = (MethodDef) isInfo;
        int isClassIndex = isFieldDef.getClassIndex();
        int isClassNameIndex = ((ClassCp) constantPool.infos[isClassIndex - 1]).getNameIndex();
        int isNTIdx = isFieldDef.getNameAndTypeIndex();
        NameAndType isNT = (NameAndType) constantPool.infos[isNTIdx - 1];
        return new InvokespecialInst(Utils.getString(constantPool, isClassNameIndex),
            Utils.getString(constantPool, isNT.getNameIndex()),
            Utils.getString(constantPool, isNT.getDescriptionIndex()));
      case 0xb8:
        ConstantInfo methodinfo = constantPool.infos[stream.readUnsignedShort() - 1];
        MethodDef methodDef = (MethodDef) methodinfo;
        NameAndType nat = (NameAndType) constantPool.infos[methodDef.nameAndTypeIndex - 1];

        String methodName = Utils.getString(constantPool, nat.getNameIndex());
        String descriptor = Utils.getString(constantPool, nat.getDescriptionIndex());
        return new InvokestaticInst(methodName, descriptor);
      case 0xbb:
        ConstantInfo newInfo = constantPool.infos[stream.readUnsignedShort() - 1];
        String newClassName = Utils.getString(constantPool, ((ClassCp) newInfo).getNameIndex());
        return new NewInst(newClassName);
      default:
        return null;
//        throw new UnsupportedOperationException("unknown op code");
    }

  }
}
