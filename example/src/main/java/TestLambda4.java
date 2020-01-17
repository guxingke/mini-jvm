import java.util.function.Supplier;

public class TestLambda4 {
  public static void main(String[] args) {
    
    Supplier<Integer> supplier = () -> new Integer(10);
    int test = supplier.get();
    System.out.println(test);
  }
}
