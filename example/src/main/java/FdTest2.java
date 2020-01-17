import java.io.FileOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;


public class FdTest2 {

  public static void main(String[] args) throws IOException {
    FileOutputStream out = new FileOutputStream(FileDescriptor.out);
    out.write('a');
    out.write('b');
    out.flush();
    out.close();
  }
}
