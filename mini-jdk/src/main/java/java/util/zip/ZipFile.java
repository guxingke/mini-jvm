package java.util.zip;

import java.io.InputStream;

public class ZipFile {
  public native void init(String path);

  public ZipFile(String path) {
    init(path);
  }

  public native ZipEntry getEntry(String name);

  public native InputStream getInputStream(ZipEntry entry);
}