import java.util.ArrayList;

public class TestArrayList {
  public static void main(String[] args) {
      ArrayList<String> list = new ArrayList<>(2);
      list.add("1");
      list.add("2");
      
      int size = list.size();
      String l1 = list.get(0);
      
      System.out.println(size);
      System.out.println(l1);
  }
}
