interface Interface1 {
  InterfaceObj x = new InterfaceObj();

  void v1();
}

interface Interface2 extends Interface1 {
  InterfaceObj y = new InterfaceObj();
}

class InterfaceImpl1 implements Interface1 {
  public void v1() {
    System.out.println(2);
  }

  public static void main(String[] args) {
    Interface1 i1 = new InterfaceImpl1();
    i1.v1();

    System.out.println(i1.x.val);
  }
}

class InterfaceImpl2 implements Interface2 {
  public void v1() {
    System.out.println(3);
  }

  public static void main(String[] args) {
    Interface2 i2 = new InterfaceImpl2();
    i2.v1();

    System.out.println(i2.y.val);
  }
}

class InterfaceObj {
  int val = 10;
  {
    System.out.println(1);
  }
}

