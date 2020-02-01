import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

public class FileTest6 {

  public static void main(String[] args) throws IOException {

    try (FileInputStream fis = new FileInputStream(FileDescriptor.in)) {
      while (true) {
        int read = fis.read();
        System.out.println(read);
      }
    }
  }
}
