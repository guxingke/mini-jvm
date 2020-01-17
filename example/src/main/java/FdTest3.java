import java.io.FileOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;


public class FdTest3 {

  public static void main(String[] args) throws IOException {
    FileOutputStream err = new FileOutputStream(FileDescriptor.err);
    byte[] bytes = new byte[6];
    bytes[0] = 'h';
    bytes[1] = 'e';
    bytes[2] = 'l';
    bytes[3] = 'l';
    bytes[4] = 'o';
    bytes[5] = '\n';
    err.write(bytes);
    err.flush();
    err.close();
  }
}
