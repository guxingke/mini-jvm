public class StaticField {

  public static boolean zv;
  public static char cv;
  public static byte bv;
  public static byte sv;
  public static int iv;
  public static long lv;
  public static float fv;
  public static double dv;

  static {
    // test static field int and get
    System.out.println(zv);
    System.out.println(cv);
    System.out.println(bv);
    System.out.println(sv);
    System.out.println(iv);
    System.out.println(lv);
    System.out.println(fv);
    System.out.println(dv);

    // set
    zv = false;
    cv = '1';
    bv = 3;
    sv = 4;
    iv = 5;
    lv = 0L;
    fv = 1f;
    dv = 0d;
  }

  public static void main(String[] args) {
    // get
    System.out.println(zv);
    System.out.println(cv);
    System.out.println(bv);
    System.out.println(sv);
    System.out.println(iv);
    System.out.println(lv);
    System.out.println(fv);
    System.out.println(dv);
  }
}
