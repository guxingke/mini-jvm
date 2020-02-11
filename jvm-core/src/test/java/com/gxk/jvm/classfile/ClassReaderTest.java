package com.gxk.jvm.classfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.gxk.jvm.instruction.Instruction;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;

public class ClassReaderTest {

//  @Test
//  public void test_hello() throws IOException {
//    String hello = "example/Hello.class";
//    test(hello);
//  }
//
//  @Test
//  public void test_loop() throws IOException {
//    String loop = "example/Loop1.class";
//    test(loop);
//  }
//
//  @Test
//  public void test_int1() throws IOException {
//    String int1 = "example/Int3Impl.class";
//    test(int1);
//  }
//
//  @Test
//  public void test_exception() throws IOException {
//    String clazz = "/Users/gxk/toy/java/mini-jvm/example/target/classes/Hello.class";
//    test(clazz);
//  }


  public void test(String p) throws IOException {
    ClassFile cf = ClassReader.read(new DataInputStream(new FileInputStream(p)));
    assertNotNull(cf);
  }

  @Test
  public void testReadInst_main() throws IOException {
    String main = "033c043d1c100aa3000d1b1c603c840201a7fff3b1";
    byte[] bytes = toBytes(main);

    Instruction[] instructions = ClassReader.readByteCode(bytes, null);
    assertEquals(14, instructions.length);
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

}