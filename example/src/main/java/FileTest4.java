import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileTest4 {

  public static void main(String[] args) throws IOException {
    String userDir = System.getProperty("user.dir");

    if (userDir.endsWith("jvm-core")) {
      int lastIdx = userDir.lastIndexOf(File.separator);
      userDir = userDir.substring(0, lastIdx);
    }

    String filePath = userDir + File.separator + "HelloWorld.class";

    FileInputStream fis = new FileInputStream(filePath);
    DataInputStream dis = new DataInputStream(fis);

    // magic
    int magic = dis.readInt();
    String hexStr = Integer.toHexString(magic);
    System.out.println("magic = " + hexStr);

    // minor_version
    int minorVersion = dis.readUnsignedShort();
    System.out.println("minorVersion = " + minorVersion);

    // majorVersion
    int majorVersion = dis.readUnsignedShort();
    System.out.println("majorVersion = " + majorVersion);

    // constant pool size
    int constantPoolSize = dis.readUnsignedShort();
    System.out.println("constantPoolSize = " + constantPoolSize);

    for (int i = 1; i < constantPoolSize; i++) {
      int tag = dis.readUnsignedByte();
      if (tag == 10 /* Methodref */) {
        int mrni = dis.readUnsignedShort();
        int mrnti = dis.readUnsignedShort();

        String mrmsg = "[CP " + tag + "] [MethodRef] [" + mrni + "] [NameIndex] [" + mrnti + "] [NameAndTypeIndex]";
        System.out.println(mrmsg);
        continue;
      }
      if (tag == 9 /* Fieldref */) {
        int frci = dis.readUnsignedShort();
        int frnti = dis.readUnsignedShort();

        String frmsg = "[CP " + tag + "] [FieldRef] [" + frci + "] [Class] [" + frnti + "] [NameAndTypeIndex]";
        System.out.println(frmsg);
        continue;
      }
      if (tag == 8 /* String */) {
        int ssi = dis.readUnsignedShort();
        String smsg = "[CP " + tag + "] [String] [" + ssi + "] [StringIndex]";
        System.out.println(smsg);
        continue;
      }
      if (tag == 7 /* Class */) {
        int cni = dis.readUnsignedShort();
        String cmsg = "[CP " + tag + "] [Class] [" + cni + "] [StringIndex]";
        System.out.println(cmsg);
        continue;
      }
      if (tag == 1 /* UTF8 */) {
        int ulen = dis.readUnsignedShort();
        byte[] bytes = new byte[ulen];
        for (int j = 0; j < ulen; j++) {
          bytes[j] = dis.readByte();
        }

        String umsg = "[CP " + tag + "] [UTF8] [" + ulen + "] [length] [ " + "todo" + " ] [bytes]";
        System.out.println(umsg);

        continue;
      }
      if (tag == 12 /* NameAndType */) {
        int ntni = dis.readUnsignedShort();
        int ntdi = dis.readUnsignedShort();

        String ntmsg = "[CP " + tag + "] [NameAndType] [" + ntni + "] [NameIndex] [" + ntdi + "] [DescriptorIndex]";
        System.out.println(ntmsg);

        continue;
      }
    }

    int accessFlag = dis.readUnsignedShort();
    System.out.println("accessFlag = " + accessFlag);

    int thisClass = dis.readUnsignedShort();
    System.out.println("thisClass = " + thisClass);

    int superClass = dis.readUnsignedShort();
    System.out.println("superClass = " + superClass);

    int interfaceCount = dis.readUnsignedShort();
    System.out.println("interfaceCount = " + interfaceCount);

    int fieldCount = dis.readUnsignedShort();
    System.out.println("fieldCount = " + fieldCount);

    int methodCount = dis.readUnsignedShort();
    System.out.println("methodCount = " + methodCount);

//      Methods methods = readMethods(is, methodCount, constantPool);

//      int attributeCount = dis.readUnsignedShort();
//      Attributes attributes = readAttributes(is, attributeCount, constantPool);

    dis.close();
    fis.close();
  }
}
