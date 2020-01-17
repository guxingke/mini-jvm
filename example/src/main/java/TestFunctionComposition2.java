import java.util.function.Function;

public class TestFunctionComposition2 {

  public static void main(String[] args) {
    Function<Integer,Integer> f1 = it -> { return it + 1; };
    Function<Integer,Integer> f2 = it -> { return it - 10; };

    Function<Integer,Integer> f4 = f1.andThen(f2);

    int ret = f4.apply(0);

    System.out.println(ret);
  }
}
