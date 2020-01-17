public class Int3Impl2 implements Int3 {
  @Override
  public void test(){
    System.out.println("test2");
  }

  public static void main(String[] args) {
    Int3 obj = new Int3Impl2();
    obj.test();
  }
}
