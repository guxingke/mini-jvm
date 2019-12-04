import java.util.function.Function;

public class TestFunctionComposition3 {

  public static void main(String[] args) {
    Function<Integer,Integer> f1 = it -> { return it + 1; };
    Function<Integer,Integer> f2 = it -> { return it - 10; };

    Function<Integer,Integer> f4 = it -> {int val = f1.apply(it); int val2 = f2.apply(val); return val2; };

    int ret = f4.apply(0);

    System.out.println(ret);
  }
}
