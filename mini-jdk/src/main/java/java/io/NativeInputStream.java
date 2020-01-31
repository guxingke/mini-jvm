package java.io;

public class NativeInputStream extends InputStream {

  @Override
  public native int read() throws IOException;

  @Override
  public native int available() throws IOException;

  @Override
  public native void close() throws IOException;
}
