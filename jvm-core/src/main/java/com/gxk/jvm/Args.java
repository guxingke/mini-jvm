package com.gxk.jvm;

import com.gxk.jvm.util.EnvHolder;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * java -cp target/mini.jar com.gxk.Main xxxxxx
 */
public class Args {

  private static final String MINUS_VERSION = "-version";
  private static final String MINUS_HELP = "-help";
  private static final String MINUS_VERBOSE = "-verbose";
  private static final String MINUS_VERBOSE_TRACE = "-verbose:trace";
  private static final String MINUS_VERBOSE_CLASS = "-verbose:class";
  private static final String MINUS_VERBOSE_DEBUG = "-verbose:debug";

  private static final String MINUS_CP = "-cp";
  private static final String MINUS_JAR = "-jar";

  boolean version;
  boolean help;
  boolean verbose;
  boolean verboseTrace;
  boolean verboseClass;
  boolean verboseDebug;

  public String classpath = ".";
  public String clazz;
  public String[] args;

  public static Args parseArgs(String... cliArgs) {
    Args args = new Args();

    if (Objects.equals(MINUS_VERSION, cliArgs[0])) {
      args.version = true;
      return args;
    }

    if (Objects.equals(MINUS_HELP, cliArgs[0])) {
      args.help = true;
      return args;
    }

    int idx = 0;
    int tries = 0;
    while (true) {
      String tmp = cliArgs[idx];
      if (Objects.equals(MINUS_CP, tmp)) {
        break;
      }
      if (Objects.equals(MINUS_JAR, tmp)) {
        break;
      }
      if (tries > 200) {
        System.out.println("parse args in loop. check input args.");
        throw new IllegalArgumentException();
      }
      if (Objects.equals(MINUS_VERBOSE, cliArgs[idx])) {
        idx++;
        args.verbose = true;
      }

      if (Objects.equals(MINUS_VERBOSE_TRACE, cliArgs[idx])) {
        idx++;
        args.verboseTrace = true;
      }

      if (Objects.equals(MINUS_VERBOSE_CLASS, cliArgs[idx])) {
        idx++;
        args.verboseClass = true;
      }

      if (Objects.equals(MINUS_VERBOSE_DEBUG, cliArgs[idx])) {
        idx++;
        args.verboseDebug = true;
      }

      tries++;
    }

    if (MINUS_CP.equals(cliArgs[idx])) {
      idx++;
      args.classpath = cliArgs[idx++];
    }

    if (MINUS_JAR.equals(cliArgs[idx])) {
      idx++;
      String mainJar = cliArgs[idx++];
      args.classpath = args.classpath + ":" + mainJar;
      args.clazz = parseMainClass(mainJar);
    } else {
      args.clazz = cliArgs[idx++];
    }

    if (cliArgs.length > idx) {
      String[] programArgs = new String[cliArgs.length - idx];
      System.arraycopy(cliArgs, idx, programArgs, 0, programArgs.length);
      args.args = programArgs;
    }

    return args;
  }

  private static String parseMainClass(String mainJar) {
    String userDir = System.getProperty("user.dir");
    String path = userDir + EnvHolder.FILE_SEPARATOR + mainJar;
    try {
      ZipFile file = new ZipFile(path);
      ZipEntry entry = file.getEntry("META-INF/MANIFEST.MF");

      InputStream is = file.getInputStream(entry);
      String line;
      while ((line = readLine(is)) != null) {
        if (line.startsWith("Main-Class: ")) {
          return line.substring(12);
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
    throw new IllegalArgumentException("Not found main class");
  }

  private static String readLine(InputStream is) throws IOException {
    StringBuilder line = new StringBuilder();
    int b = is.read();
    if (b < 0) {
      return null;
    }
    while (b > 0) {
      char c = (char) b;
      if (c == '\r' | c == '\n') {
        break;
      }
      if (c == '.') {
        c = '/';
      }
      line.append(c);
      b = is.read();
    }
    return line.toString();
  }
}
