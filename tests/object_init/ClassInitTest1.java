public class ClassInitTest1 {
  static {
    System.out.println(1);
  }
  public static void main(String[] args) {
    System.out.println(2);
  }
}
class ClassInitTest2 extends ClassInitTest1{
  static {
    System.out.println(2);
  }
  public static void main(String[] args) {
    System.out.println(3);
  }
}
class ClassInitTest3 extends ClassInitTest2 {
  static {
    System.out.println(3);
  }
  public static void main(String[] args) {
    System.out.println(4);
  }
}
