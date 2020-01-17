import java.util.function.Function;

public class TestLambda5 {
  public static void main(String[] args) {
    Function<String, String> d = (it) -> it + " " + it;
    String test = d.apply("test");
    System.out.println(test);
  }
}
