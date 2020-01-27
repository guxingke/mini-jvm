import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileTest {

  public static void main(String[] args) {
    String userDir = System.getProperty("user.dir");

    if (userDir.endsWith("jvm-core")) {
      int lastIdx = userDir.lastIndexOf(File.separator);
      userDir = userDir.substring(0, lastIdx);
    }

    String filePath = userDir + File.separator + "README.md";

    File file = new File(filePath);
    String name = file.getName();
    System.out.println(name);

    String path = file.getPath();
    System.out.println(path);

    boolean exists = file.exists();
    System.out.println(exists);
  }
}
