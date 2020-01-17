import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

  public static void main(String[] args) {
    AtomicInteger ai = new AtomicInteger();
    int x = ai.get();
    System.out.println(x);

    int x2 = ai.incrementAndGet();
    System.out.println(x2);

    int x3 = ai.incrementAndGet();
    System.out.println(x3);
  }
}
