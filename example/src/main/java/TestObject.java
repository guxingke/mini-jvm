public class TestObject {

  private int level;

  public TestObject(int level){
    this.level = level;
  }

  public int getLevel() {
    return this.level;
  }
  
  public void setLevel(int level) {
    this.level = level;
  }

  public static void main(String[] args) {
    TestObject object = new TestObject(10);
    int level = object.getLevel();
    System.out.println(level);

    TestObject obj2 = new TestObject(20);
    int newLevel = obj2.getLevel();
    System.out.println(newLevel);
  }
}
