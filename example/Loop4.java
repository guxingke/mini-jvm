public class Loop4 {
  public static void main(String[] args){
    int sum = sum10();
    System.out.println(sum);
  }

  public static int sum10() {
    int sum = 0;
    for (int i = 1; i <= 10; i++) {
      sum += i;
    }
    return sum;
  }
}
