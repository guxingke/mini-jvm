package com.gxk.jvm;

import com.gxk.jvm.ext.bc.ByteCodeGenerator;
import com.gxk.jvm.ext.bc.ByteCodeInterpreter;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args)  {
    if (args.length == 0) {
      System.out.println("usage: java [options] class [args]\n");
      return;
    }

    // special case
    if (args[0].startsWith("--")) {
      doSpecialCase(args);
      return;
    }

    Args cmd = Args.parseArgs(args);

    if (cmd.version) {
      System.out.println("java version \"1.8.0\"");
      return;
    }

    if (cmd.help) {
      InputStream is = Main.class.getClassLoader().getResourceAsStream("help.txt");
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          out.append(line).append("\n");
        }
        System.out.println(out);
      } catch (Exception e) {
        System.out.println("something bad");
      }
      return;
    }

    VirtualMachine vm = new VirtualMachine();
    vm.run(cmd);
  }

  // 一些有趣的玩意儿
  private static void doSpecialCase(String[] args) {
    // parse args
    switch (args[0]) {
      case "--":
        // byte code interpreter
        String file = args[1];

        if (args.length == 2) {
          ByteCodeInterpreter.interpreter(file);
          break;
        }

        int size = args.length - 2;
        String[] args2 = new String[size];
        System.arraycopy(args, 2, args2, 0, args2.length);

        Integer[] pargs = new Integer[size];
        for (int i = 0; i < args2.length; i++) {
          pargs[i] = Integer.parseInt(args2[i]);
        }

        ByteCodeInterpreter.interpreter(file, pargs);
        break;
      case "--bc":
        String clazzPath = args[1];
        String methodName = "bc";
        if (args.length == 3) {
          methodName = args[2];
        }

        ByteCodeGenerator.gen(clazzPath, methodName);
        break;

    }
  }
}
