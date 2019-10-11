package com.gxk.jvm.classpath;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Classpath {

  public static Entry parse(String classpath) {
    if (classpath.contains(":")) {
      return doParseCompositeEntry(classpath);
    }
    Entry entry = parseEntry(classpath);
    if (entry == null) {
      throw new IllegalArgumentException("un parse classpath " + classpath);
    }
    // ./:./*:lib/:lib/*:.*
    return entry;
  }

  public static Entry doParseCompositeEntry(String classpath) {
    List<Entry> entries = new ArrayList<>();
    for (String path : classpath.split(":")) {
      Entry entry = parseEntry(path);
      if (entry == null) {
        throw new IllegalArgumentException("un parse classpath " + classpath);
      }
      entries.add(entry);
    }
    return new CompositeEntry(entries);
  }

  public static Entry parseEntry(String path) {
    Entry entry = null;
    if (isDir(path)) {
      entry = doParseDir(path);
    } else if (isJar(path)) {
      entry = doParseJar(path);
    } else if (isWildcard(path)) {
      entry = doParseWildcard(path);
    }
    return entry;
  }

  public static Entry doParseWildcard(String path) {
    List<Entry> entries = Arrays.stream(
        Objects.requireNonNull(Paths.get(path).toFile().list((dir, name) -> isJar(name))))
        .map(Classpath::doParseJar).collect(
            Collectors.toList());
    return new CompositeEntry(entries);
  }

  public static Entry doParseJar(String path) {
    return new JarEntry(Paths.get(path));
  }

  public static Entry doParseDir(String path) {
    return new DirEntry(Paths.get(path));
  }

  public static boolean isDir(String path) {
    return Paths.get(path).toFile().isDirectory();
  }

  public static boolean isWildcard(String path) {
    return path.endsWith("/*");
  }

  public static boolean isJar(String path) {
    return path.endsWith(".jar");
  }
}
