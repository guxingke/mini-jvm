import java.util.function.Consumer;

public class TestLambda2 {
  public static void main(String[] args) {

    Consumer<String> cs = it -> System.out.println(it);
    cs.accept("hello lambda");
  }
}
