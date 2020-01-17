import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamTest {

  public static void main(String[] args) {
    PrintStream err = new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.err)), true);
    err.println(1);
  }
}
