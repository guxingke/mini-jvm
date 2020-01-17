import java.util.HashMap;

public class TestMap2 {
  
  public static void main(String[] args) {
    HashMap<Integer, String> map = new HashMap<>();
    map.put(1,"11");
    map.put(2,"22");

    String tmp = map.get(1);

    System.out.println(tmp);
  }
}
