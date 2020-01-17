import java.util.function.*;

public class Curry2Args {
   public static void main(String[] args) {
      Function<String, Function<String, String>> sum = a -> b -> a + b;
      Function<String, String> hi = sum.apply("Hi ");
      String val = hi.apply("Ho ");

      System.out.println(val);
   }
}
