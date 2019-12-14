import java.util.ArrayList;

public class TestList2 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      list.add(new Integer(i));
    }
    int size = list.size();
    System.out.println(size);

    Integer tmp = list.get(10);
    System.out.println(tmp);
  }
}
