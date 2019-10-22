public class TestObject3 extends TestObject {

  TestObject3(int num) {
    super(num);
  }

  public static void main(String[] args) {
    TestObject3 object = new TestObject3(10);
    int level = object.getLevel();
    System.out.println(level);
  }
}
