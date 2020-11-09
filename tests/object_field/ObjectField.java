
class ObjectField1 {
  int iv;
  private int piv;
  
  ObjectField1() {
    this.iv = 1;
    this.piv = 5;

    System.out.println(this.iv);
    System.out.println(this.piv);
  }

  public static void main(String[] args) {
    new ObjectField1();
  }
}

class ObjectField2 extends ObjectField1 {
  private int piv;

  ObjectField2() {
    this.piv = 4;

    System.out.println(this.iv);
    System.out.println(super.iv);
    System.out.println(this.piv);

    this.iv = 3;
    System.out.println(super.iv);
    // System.out.println(super.piv); // 编译失败
  }

  public static void main(String[] args) {
    new ObjectField2();
  }
}
