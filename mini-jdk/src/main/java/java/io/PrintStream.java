package java.io;

public class PrintStream extends FilterOutputStream {

  public PrintStream(OutputStream out, boolean auto) {
    super(out);
  }

  public void flush() {
    try {
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void close() {
    try {
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void write(String s) {
    byte[] bytes = s.getBytes();
    try {
      out.write(bytes);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void write(int b) {
    try {
      out.write(b);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void write(byte buf[], int off, int len) {
    try {
      out.write(buf,off,len);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void print(boolean b) {
    write(b ? "true" : "false");
  }

  public void print(char c) {
    write(String.valueOf(c));
  }

  public void print(int i) {
    write(String.valueOf(i));
  }

  public void print(long l) {
    write(String.valueOf(l));
  }

  public void print(float f) {
    write(String.valueOf(f));
  }

  public void print(double d) {
    write(String.valueOf(d));
  }

  public void print(char s[]) {
  }

  public void print(String s) {
    if (s == null) {
      s = "null";
    }
    write(s);
  }

  public void print(Object obj) {
    write(String.valueOf(obj));
  }

  private void newLine() {
    print("\n");
  }

  public void println() {
    newLine();
  }

  public void println(boolean x) {
    synchronized (this) {
      print(x);
      newLine();
    }
  }

  public void println(char x) {
    synchronized (this) {
      print(x);
      newLine();
    }
  }

  public void println(int x) {
    synchronized (this) {
      print(x);
      newLine();
    }
  }

  public void println(long x) {
    synchronized (this) {
      print(x);
      newLine();
    }
  }

  public void println(float x) {
    synchronized (this) {
      print(x);
      newLine();
    }
  }

  public void println(double x) {
    synchronized (this) {
      print(x);
      newLine();
    }
  }

  public void println(char x[]) {
    synchronized (this) {
      print(x);
      newLine();
    }
  }

  public void println(String x) {
    synchronized (this) {
      print(x);
      newLine();
    }
  }

  public void println(Object x) {
    String s = String.valueOf(x);
    synchronized (this) {
      print(s);
      newLine();
    }
  }
}
