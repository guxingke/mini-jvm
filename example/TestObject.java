public class TestObject {

  private String name;
  private Integer level;


  public TestObject(){}

  public TestObject(String name, Integer level) {
    this.name = name;
    this.level = level;
  }
  
  public String getName() {
    return this.name;
  }

  public Integer getLevel() {
    return this.level;
  }
}
