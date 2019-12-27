public class TestClass {
  public static void main(String[] args) {
    TestClass test = new TestClass();
    Class clazz = test.getClass();

    String name = clazz.getName();

    System.out.println(name);
  }
}
