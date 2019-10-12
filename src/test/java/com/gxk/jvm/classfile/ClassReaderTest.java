package com.gxk.jvm.classfile;

import static org.junit.Assert.assertNotNull;

import com.gxk.jvm.instruction.Instruction;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

public class ClassReaderTest {

  @Test
  public void test_hello() throws IOException {
    String hello = "example/Hello.class";
    test(hello);
  }

  @Test
  public void test_loop() throws IOException {
    String loop = "example/Loop1.class";
    test(loop);
  }

  public void test(String p) throws IOException {
    Path path = Paths.get(p);
    ClassFile cf = ClassReader.read(path);

    assertNotNull(cf);

//    System.out.println(cf);
  }

  //  int sum = 0;
//    for (int i = 1; i <= 10; i++) {
//    sum += i;
//  }
//  return;
  @Test
  public void testReadInst_main() throws IOException {
    String main = "033c043d1c100aa3000d1b1c603c840201a7fff3b1";
    byte[] bytes = toBytes(main);

    Instruction[] instructions = ClassReader.readByteCode(bytes, null);
    System.out.println(instructions.length);

    System.out.println(bytesToHex(bytes));
  }

  public static byte[] toBytes(String str) {
    if (str == null || str.trim().equals("")) {
      return new byte[0];
    }

    byte[] bytes = new byte[str.length() / 2];
    for (int i = 0; i < str.length() / 2; i++) {
      String subStr = str.substring(i * 2, i * 2 + 2);
      bytes[i] = (byte) Integer.parseInt(subStr, 16);
    }

    return bytes;
  }

  public static String bytesToHex(byte[] a) {
    StringBuilder sb = new StringBuilder(a.length * 2);
    for (byte b : a) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}