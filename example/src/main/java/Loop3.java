public class Loop3 {
  public static void main(String[] args){
    int sum = sum(10);
    System.out.println(sum);
  }

  public static int sum(int n) {
    int sum = 0;
    for (int i = 1; i <= n; i++) {
      sum += i;
    }
    return sum;
  }
}
