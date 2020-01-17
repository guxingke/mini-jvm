import java.util.function.Supplier;

public class TestClosure {

  Supplier<String> makeFun() {
    String xx = "x";
    return () -> xx;
  }

  public static void main(String[] args) {

    TestClosure tc = new TestClosure();
    String f1 = tc.makeFun().get();
    String f2 = tc.makeFun().get();

    System.out.println(f1);
    System.out.println(f2);
  }
}
