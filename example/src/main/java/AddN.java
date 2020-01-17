public class AddN{

  public static void main(String[] args) {
    int ret = addN(3);
    System.out.println(ret);
  }

  public static int addN(int n) {
    if(n==0) return 0;
    if(n==1) return 1;

    return n + addN(n-1);
  }
}
