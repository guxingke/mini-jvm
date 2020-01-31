import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarTest {

  public static void main(String[] args) throws IOException {
    String userDir = System.getProperty("user.dir");

    if (userDir.endsWith("jvm-core")) {
      int lastIdx = userDir.lastIndexOf(File.separator);
      userDir = userDir.substring(0, lastIdx);
    }

    ZipFile file = new ZipFile(userDir + File.separator + "test.jar");
    ZipEntry entry = file.getEntry("META-INF/MANIFEST.MF");

    InputStream is = file.getInputStream(entry);
    String line;
    while ((line = readLine(is)) != null) {
      if (line.startsWith("Main-Class: ")) {
        System.out.println(line.substring(12));
      }
    }
  }

  private static String readLine(InputStream is) throws IOException {
    StringBuilder line = new StringBuilder();
    int b = is.read();
    if (b < 0) {
      return null;
    }
    while (b > 0) {
      char c = (char) b;
      if (c == '\r' | c == '\n') {
        break;
      }
      if (c == '.') {
        c = '/';
      }
      line.append(c);
      b = is.read();
    }
    return line.toString();
  }
}
