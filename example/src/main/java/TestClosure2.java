import java.util.function.Supplier;

public class TestClosure2 {

  Supplier<Integer> makeFun() {
    Integer xx = 10;
    return () -> xx;
  }

  public static void main(String[] args) {

    TestClosure2 tc = new TestClosure2();
    int f1 = tc.makeFun().get();
    int f2 = tc.makeFun().get();

    System.out.println(f1);
    System.out.println(f2);
  }
}
