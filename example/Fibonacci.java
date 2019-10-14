public class Fibonacci {
  public static void main(String[] args){
    int ret = fab(20);
    System.out.println(ret);
  }

  public static int fab(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1 || n==2) {
      return 1;
    }
    return fab(n-1) + fab(n-2);
  }
}
