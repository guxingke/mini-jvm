import java.util.HashMap;

public class TestMap {

  public static void main(String[] args) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    Integer key1 = new Integer(1);
    Integer key2 = new Integer(2);

    map.put(key1, key1);
    map.put(key2, key2);

    int size = map.size();
    Integer tmp = map.get(key1);

    System.out.println(size);
    System.out.println(tmp);
  }
}
