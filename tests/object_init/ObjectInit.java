class ObjectInit1 {
  {
    System.out.println(1);
  }

  ObjectInit1() {
    System.out.println(2);
  }

  public static void main(String[] args) {
    new ObjectInit1();
  }
}
class ObjectInit2 extends ObjectInit1 {
  {
    System.out.println(3);
  }

  ObjectInit2() {
    System.out.println(4);
  }

  public static void main(String[] args) {
    new ObjectInit2();
  }
}
class ObjectInit3 extends ObjectInit2 {
  {
    System.out.println(3);
  }

  ObjectInit3() {
    System.out.println(2);
  }

  public static void main(String[] args) {
    new ObjectInit3();
  }
}
