import java.nio.charset.Charset;

public class CharsetTest {

  public static void main(String[] args) {
    Charset charset = Charset.defaultCharset();
    String name = charset.name();
    System.out.println(name);
  }
}
