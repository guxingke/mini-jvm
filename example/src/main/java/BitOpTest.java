public class BitOpTest {
  public static void main(String[] args) {
    byte[] bytes = new byte[] {-28, -72, -83};

    for (int i = 0; i < bytes.length; i++) {
      byte b = bytes[i];
      if (b > 0) {
        char c = (char) b;
        System.out.println(c);
        continue;
      }

      i++;
      int b2 = bytes[i];
      if ((b & 0xF0) != 0xE0) {
        char c = (char) ((b & 0x1F) << 6 | b2 & 0x3F);
        System.out.println("2 " + c);
        continue;
      }

      i++;
      int b3 = bytes[i];
      char c = (char) ((b & 0x0F) << 12 | (b2 & 0x3F) << 6 | b3 & 0x3F);
      System.out.println("3 " + c);
    }
  }
}

