package com.gxk.jvm.classfile;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClassReader {

  public ClassFile read(InputStream inputStream) throws IOException {
    DataInputStream is = new DataInputStream(new BufferedInputStream(inputStream));

    int magic = is.readInt();
    System.out.println("magic = " + magic);
    System.out.println("Integer.toHexString(magic) = " + Integer.toHexString(magic));

    int minorVersion = is.readUnsignedShort();
    System.out.println("minorVersion = " + minorVersion);

    int majorVersion = is.readUnsignedShort();
    System.out.println("majorVersion = " + majorVersion);

    int cpSize = is.readUnsignedShort();
    System.out.println("cpSize = " + cpSize);

    // read constants pool
    readConstantPool(is, cpSize);

    // read access_flag
    int accessFlag = is.readUnsignedShort();
    System.out.println("accessFlag = " + accessFlag);
    System.out.println("Integer.toHexString(accessFlag) = " + Integer.toHexString(accessFlag));

    // this_class
    int thisClass = is.readUnsignedShort();
    System.out.println("thisClass = " + thisClass);

    // superClass
    int superClass = is.readUnsignedShort();
    System.out.println("superClass = " + superClass);

    // interfaces
    int interfaceCount = is.readUnsignedShort();
    System.out.println("interfaceCount = " + interfaceCount);

    // fields
    int fieldCount = is.readUnsignedShort();
    System.out.println("fieldCount = " + fieldCount);

    // methods
    int methodCount = is.readUnsignedShort();
    System.out.println("methodCount = " + methodCount);
    readMethods(is, methodCount);

    // attribute
    int attributeCount = is.readUnsignedShort();
    System.out.println("attributeCount = " + attributeCount);

    for (int i = 0; i < attributeCount; i++) {
      int attributeNameIndex = is.readUnsignedShort();
      System.out.println("attributeNameIndex = " + attributeNameIndex);
      int attributeLength = is.readInt();
      System.out.println("attributeLength = " + attributeLength);
//      byte[] bytes = is.readNBytes(attributeLength);
//      System.out.println("bytes = " + byteArrayToHex(bytes));
    }
    
    return new ClassFile();
  }

  private void readMethods(DataInputStream is, int methodCount) throws IOException {
    for (int i = 0; i < methodCount; i++) {
      int accessFlag = is.readUnsignedShort();
      int nameIndex = is.readUnsignedShort();
      int descriptorIndex = is.readUnsignedShort();
      int attributesCount = is.readUnsignedShort();

      System.out.println("attributesCount = " + attributesCount);

      for (int i1 = 0; i1 < attributesCount; i1++) {
        int attributeNameIndex = is.readUnsignedShort();
        // ignore, 暂时只是 code
        int attributeLength = is.readInt();
        int maxStack = is.readUnsignedShort();
        int maxLocals = is.readUnsignedShort();
        int codeLength = is.readInt();
        // read code
//        byte[] bytes = is.readNBytes(codeLength);

        int exceptionTableLength = is.readUnsignedShort();

        int codeAttributeCount = is.readUnsignedShort();
        for (int i2 = 0; i2 < codeAttributeCount; i2++) {
          int codeAttributeNameIndex = is.readUnsignedShort();
          int codeAttributeLength = is.readInt();
//          byte[] bytes1 = is.readNBytes(codeAttributeLength);
        }
      }
    }
  }

  private ConstantInfo[] readConstantPool(DataInputStream is, int cpSize) throws IOException {
    for (int i = 0; i < cpSize - 1; i++) {
      int tag = is.readUnsignedByte();

      ConstantPoolInfoEnum infoEnum = ConstantPoolInfoEnum.of(tag);
      if (infoEnum == null) {
        throw new IllegalStateException();
      }

      System.out.print(String.format("#%02d  ", i + 1));
      switch (infoEnum) {
        case CONSTANT_Fieldref:
        case CONSTANT_Methodref:
        case CONSTANT_InterfaceMethodref:
          int classIndex = is.readUnsignedShort();
          int nameAndTypeIndex = is.readUnsignedShort();
          System.out.println(String.format("tag: %d %s , class_index: %d , name_and_type_index: %d", tag, infoEnum.name(), classIndex, nameAndTypeIndex));
          break;
        case CONSTANT_String:
          int stringIndex = is.readUnsignedShort();
          System.out.println(String.format("tag: %d %s , string_index: %d", tag, infoEnum.name(), stringIndex));
          break;
        case CONSTANT_Class:
          int nameIndex = is.readUnsignedShort();
          System.out.println(String.format("tag: %d %s , name_index: %d", tag, infoEnum.name(), nameIndex));
          break;
        case CONSTANT_NameAndType:
          int nameIndex2 = is.readUnsignedShort();
          int descriptorIndex = is.readUnsignedShort();
          System.out.println(String.format("tag: %d %s , name_index: %d , descriptor_index: %d", tag, infoEnum.name(), nameIndex2, descriptorIndex));
          break;
        case CONSTANT_Utf8:
          int length = is.readUnsignedShort();
//          byte[] bytes = is.readNBytes(length);
//          String str = new String(bytes);
//          System.out.println(String.format("tag: %d %s , length: %d , str: %s", tag, infoEnum.name(), length, str));
          break;
      }
    }
    return null;
  }

  public static String byteArrayToHex(byte[] a) {
    StringBuilder sb = new StringBuilder(a.length * 2);
    for(byte b: a)
      sb.append(String.format("%02x", b));
    return sb.toString();
  }
}
