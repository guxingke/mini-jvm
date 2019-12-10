
public class TestException2 {
  public static void main(String[] args) {
    try {
      System.out.println(0);
      throw new Exception("hello");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println(1);
    } finally {
      System.out.println(2);
    }
  }
}
