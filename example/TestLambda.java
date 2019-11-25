public class TestLambda {
  public static void main(String[] args) {
    Runnable r = () ->System.out.println("hello lambda");

    r.run();
  }
}
