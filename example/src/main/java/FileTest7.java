import java.io.File;
import java.io.IOException;

public class FileTest7 {

  public static void main(String[] args) throws IOException {
    String userDir = System.getProperty("user.dir");
    File file = new File(userDir);
    boolean directory = file.isDirectory();
    System.out.println(directory);
  }
}
