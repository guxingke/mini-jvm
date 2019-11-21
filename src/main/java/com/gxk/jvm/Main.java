package com.gxk.jvm;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args)  {

    if (args.length == 0) {
      System.out.println("usage: java [options] class [args]\n");
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
}
