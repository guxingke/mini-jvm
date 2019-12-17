import java.util.HashSet;

public class TestSet {

  public static void main(String[] args) {
    HashSet<Integer> set= new HashSet<Integer>();

    Integer key1 = new Integer(1);
    Integer key2 = new Integer(2);

    set.add(key1);
    set.add(key2);
    set.add(key2);


    int size = set.size();

    boolean tmp = set.contains(key1);

    System.out.println(size);
    System.out.println(tmp);
  }
}
