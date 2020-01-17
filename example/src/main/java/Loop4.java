public class Loop4 {
  public static void main(String[] args){
    int sum = sum();
    System.out.println(sum);
  }

  public static int sum() {
    int sum = 0;
    for (int i = 1; i <= 100; i++) {
      sum += i;
    }
    return sum;
  }
}
