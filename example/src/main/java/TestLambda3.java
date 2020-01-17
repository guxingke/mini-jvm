import java.util.function.Supplier;

public class TestLambda3 {
  public static void main(String[] args) {
    
    Supplier<String> supplier = () -> "hello supplier";
    String test = supplier.get();
    System.out.println(test);
  }
}
