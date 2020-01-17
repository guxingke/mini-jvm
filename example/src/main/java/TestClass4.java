public class TestClass4 {

  public static void main(String[] args) {
    Class cls = null; 
    try {
      cls = Class.forName("TestClass");
    } catch (Exception e) { 
      System.out.println(-1);
    }

    String name = cls.getSimpleName();
    System.out.println(name);
  }
}
