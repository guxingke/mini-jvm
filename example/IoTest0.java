import java.nio.charset.Charset;

public class IoTest0 {
  public static void main(String[] args) {
    Charset charset = Charset.defaultCharset();
    System.out.println(charset.name());
  }
}
