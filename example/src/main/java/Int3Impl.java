public class Int3Impl implements Int3 {
  @Override
  public void test(){
    System.out.println("test");
  }

  public static void main(String[] args) {
    new Int3Impl().test();
  }
}
