import java.util.function.Function;

public class TestFunctionComposition4 {

  public static void main(String[] args) {
    Function<Integer,Integer> f1 = it -> { return it + 1; };
    Function<Integer,Integer> f2 = it -> { return it - 10; };

    Function<Integer,Integer> f4 = it -> f2.apply(f1.apply(it));

    int ret = f4.apply(0);

    System.out.println(ret);
  }
}
