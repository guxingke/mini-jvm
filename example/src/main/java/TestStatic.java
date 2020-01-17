public class TestStatic {
  public static int val = 10;
  public static void main(String[] args) {
    int sum = 0;
    for(int i = 1; i<= val; i++) {
      sum +=i;
    }
    System.out.println(sum);
  }
}
