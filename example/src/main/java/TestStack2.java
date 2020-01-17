public class TestStack2 {

  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();

    Integer k1 = new Integer(1);
    stack.push(k1);

    boolean isEmpty = stack.isEmpty();
    Integer tmp = stack.pop();
    System.out.println(isEmpty);
    System.out.println(tmp);
  }
}
