public class TestException4 {
  public static void main(String[] args) throws Exception {
    f3();
  }

  public static void f1() throws Exception {
    throw new Exception("hello");
  }

  public static void f2() throws Exception {
    f1();
  }

  public static void f3() throws Exception {
    f2();
  }
}
