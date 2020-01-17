import java.util.ArrayList;

public class TestList {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>(1);
    list.add(new Integer(1));

    int size = list.size();
    System.out.println(size);

    Integer tmp = list.get(0);
    System.out.println(tmp);
  }
}
