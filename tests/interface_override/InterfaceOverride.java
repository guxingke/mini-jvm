class InterfaceOverrideObj {{ System.out.println(5); }}

interface Interface11 {
  InterfaceOverrideObj x = new InterfaceOverrideObj();
  void v1();
}

interface Interface12 {
  InterfaceOverrideObj y = new InterfaceOverrideObj();
  void v2();
  static void v3() { System.out.println(3); }
}

interface Interface13 {
  InterfaceOverrideObj z = new InterfaceOverrideObj();
  default void v4() { System.out.println(4); }
}

interface Interface14 extends Interface13 {
  InterfaceOverrideObj z = new InterfaceOverrideObj();
}

class InterfaceImpl11 implements Interface11 {
  public void v1() { System.out.println(1); }

  public static void main(String[] args) {
    Interface11 i11 = new InterfaceImpl11();
    i11.v1();
  }
}

class InterfaceImpl12 implements Interface11, Interface12 {
  public void v1() { System.out.println(1); }
  public void v2() { System.out.println(2); }

  public static void main(String[] args) {
    Interface11 i11 = new InterfaceImpl12();
    i11.v1();
  }
}

class InterfaceImpl13 implements Interface11, Interface13 {
  public void v1() { System.out.println(1); }
  static { System.out.println(4); }

  public static void main(String[] args) {
    Interface11 i11 = new InterfaceImpl12();
    i11.v1();
  }
}

class InterfaceImpl14 implements Interface11, Interface14 {
  public void v1() { System.out.println(1); }
  static { System.out.println(4); }

  public static void main(String[] args) {
    Interface11 i11 = new InterfaceImpl12();
    i11.v1();
  }
}

class InterfaceImpl15 implements Interface11, Interface14 {
  public void v1() { System.out.println(1); }
  static { System.out.println(4); }

  public static void main(String[] args) {
    InterfaceImpl15 i15 = new InterfaceImpl15();
    i15.v4();
  }
}

