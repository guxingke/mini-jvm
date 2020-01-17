public class Hello2 {
  public static void main(String[] args) {
    int x = 1, y = 2, z = 3;
    int a = x + y - 2/2 + z;           // [1]
    int b = x + (y - 2)/(2 + z);       // [2]
    System.out.println("a = " + a);
    System.out.println("b = " + b);
  }
}
