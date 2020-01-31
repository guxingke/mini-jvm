import java.util.Random;

public class SwitchTest1 {

  public static void main(String[] args) {
    int arg = new Random().nextInt(1000);
    switch (arg) {
      case 0:
        System.out.println(0);
        break;
      case 100:
        System.out.println(100);
        break;
      case 200:
        System.out.println(200);
        break;
      case 900:
        System.out.println(900);
        break;
      default:
        System.out.println(arg);
    }
  }
}
