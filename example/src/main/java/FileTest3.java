import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileTest3 {

  public static void main(String[] args) throws IOException {
    String userDir = System.getProperty("user.dir");

    if (userDir.endsWith("jvm-core")) {
      int lastIdx = userDir.lastIndexOf(File.separator);
      userDir = userDir.substring(0, lastIdx);
    }

    String filePath = userDir + File.separator + "HelloWorld.class";

    FileInputStream fis = new FileInputStream(filePath);
    DataInputStream dis = new DataInputStream(fis);

    int magic = dis.readInt();
    System.out.println(magic);
    String hexStr = Integer.toHexString(magic);
    System.out.println(hexStr);

    dis.close();
    fis.close();
  }
}
