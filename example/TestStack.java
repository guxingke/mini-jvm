import java.util.Deque;
import java.util.ArrayDeque;

public class TestStack {

  public static void main(String[] args) {
    Deque<Integer> stack = new ArrayDeque<>();

    Integer k1 = new Integer(1);
    stack.push(k1);

    int size = stack.size();
    Integer tmp = stack.pop();
    System.out.println(size);
    System.out.println(tmp);
  }
}
