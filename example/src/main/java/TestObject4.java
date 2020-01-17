public class TestObject4 extends TestObject {

  TestObject4(int num) {
    super(num);
  }

  public static void main(String[] args) {
    TestObject4 object = new TestObject4(10);
    int code = object.hashCode();
    System.out.println(code);
  }
}
