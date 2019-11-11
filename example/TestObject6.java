public class TestObject6 {
  public static boolean szv;
  public static char scv;
  public static byte sbv;
  public static short ssv;
  public static int siv;
  public static float sfv;
  public static long slv;
  public static double sdv; 

  public boolean zv;
  public char cv;
  public byte bv;
  public short sv;
  public int iv;
  public float fv;
  public long lv;
  public double dv; 

  public static void main(String[] args) {
    System.out.println(szv);
    System.out.println(scv);
    System.out.println(sbv);
    System.out.println(ssv);
    System.out.println(siv);
    System.out.println(sfv);
    System.out.println(slv);
    System.out.println(sdv);
    System.out.println();


    siv = 10;

    TestObject5 obj = new TestObject5();
    System.out.println(obj.zv);
    System.out.println(obj.cv);
    System.out.println(obj.bv);
    System.out.println(obj.sv);
    System.out.println(obj.iv);
    System.out.println(obj.fv);
    System.out.println(obj.lv);
    System.out.println(obj.dv);

    System.out.println(siv);
  }
}
