
class Override1 {

  protected void t1() {
    System.out.println(1);
  }

  protected void t2() {
    System.out.println(2);
  }

  protected void t3() {
    System.out.println(3);
  }
}

class Override2 extends Override1 {

  protected void t2() {
    System.out.println(0);
  }
}

class Override3 extends Override2 {

  protected void t3() {
    System.out.println(0);
  }
}

class Override {
  public static void main(String[] args) {
    Override1 o1 = new Override1();
    o1.t1();
    o1.t2();
    o1.t3();

    Override2 o2 = new Override2();
    o2.t1();
    o2.t2();
    o2.t3();

    Override3 o3 = new Override3();
    o3.t1();
    o3.t2();
    o3.t3();
  }
}


