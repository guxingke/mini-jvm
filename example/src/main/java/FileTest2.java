import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTest2 {

  public static void main(String[] args) {
    String userDir = System.getProperty("user.dir");

    if (userDir.endsWith("jvm-core")) {
      int lastIdx = userDir.lastIndexOf(File.separator);
      userDir = userDir.substring(0, lastIdx);
    }

    String filePath = userDir + File.separator + "HelloWorld.java";
    File file = new File(filePath);

    try {
      FileInputStream fis = new FileInputStream(file);
      int available = fis.available();
      System.out.println(available);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
