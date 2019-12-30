public class TestClass5 {

  public static void main(String[] args) {
    Object obj = null;
    try {
      Class cls = Class.forName("TestClass");

      obj = cls.newInstance();
    } catch (Exception e) { 
      System.out.println(-1);
    }
    
    String name = obj.getClass().getName();
    System.out.println(name);
  }
}
