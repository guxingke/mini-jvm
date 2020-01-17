import java.io.FileOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.BufferedOutputStream;


public class BufferedOutputStreamTest {

  public static void main(String[] args) throws IOException {
    FileOutputStream out = new FileOutputStream(FileDescriptor.out);
    BufferedOutputStream o2 = new BufferedOutputStream(out, 1024);

    for (int i = 0; i < 10240; i++) {
      o2.write(i % 26 + 'a');
      if (i % 1024 == 0) {
        o2.write('\n');
      }
    }

    o2.flush();
    o2.close();
  }
}
