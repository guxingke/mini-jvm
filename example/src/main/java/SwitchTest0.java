import java.util.Random;

public class SwitchTest0 {

  public static void main(String[] args) {
    int arg = new Random().nextInt(10);
    switch (arg) {
      case 0:
        System.out.println(0);
        break;
      case 1:
        System.out.println(1);
        break;
      case 2:
        System.out.println(2);
        break;
      default:
        System.out.println(arg);
    }
  }
}
