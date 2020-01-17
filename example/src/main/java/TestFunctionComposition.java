import java.util.function.Function;

public class TestFunctionComposition {

  public static void main(String[] args) {
    Function<Integer,Integer> f1 = it -> { System.out.println(it); return it + 1; };
    Function<Integer,Integer> f2 = it -> { System.out.println(it); return it - 10; };
    Function<Integer,Integer> f3 = it -> { System.out.println(it); return it + 10; };

    Function<Integer,Integer> f4 = f1.compose(f2).andThen(f3);

    int ret = f4.apply(0);

    System.out.println(ret);
  }
}
