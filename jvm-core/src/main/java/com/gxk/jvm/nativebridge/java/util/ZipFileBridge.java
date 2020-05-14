package com.gxk.jvm.nativebridge.java.util;

import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KClass;
import com.gxk.jvm.rtda.memory.KObject;
import com.gxk.jvm.util.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public abstract class ZipFileBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/util/zip/ZipFile_init_(Ljava/lang/String;)V", frame -> {
      KObject path = (KObject) Heap.load(frame.popRef());
      String pathStr = Utils.obj2Str(path);

      ZipFile file;
      try {
        file = new ZipFile(pathStr);
      } catch (IOException e) {
        throw new UnsupportedOperationException("zip file not found");
      }

      Heap.load(frame.popRef()).setExtra(file);
    });
    MethodArea.registerMethod(
        "java/util/zip/ZipFile_getEntry_(Ljava/lang/String;)Ljava/util/zip/ZipEntry;", frame -> {
          KObject obj = (KObject) Heap.load(frame.popRef());
          ZipFile file = (ZipFile) Heap.load(frame.popRef()).getExtra();
          String entry = Utils.obj2Str(obj);

          ZipEntry zipEntry = file.getEntry(entry);
          if (zipEntry == null) {
            frame.pushRef(null);
            return;
          }

          KClass cls = MethodArea.findClass("java/util/zip/ZipEntry");
          if (cls == null) {
            cls = frame.method.clazz.classLoader.loadClass("java/util/zip/ZipEntry");
          }
          Long entryObj = cls.newObject();
          Heap.load(entryObj).setExtra(zipEntry);
          frame.pushRef(entryObj);
        });
    MethodArea.registerMethod(
        "java/util/zip/ZipFile_getInputStream_(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;",
        frame -> {
          ZipEntry entry = (ZipEntry) ((KObject) Heap.load(frame.popRef())).getExtra();
          ZipFile file = (ZipFile) ((KObject) Heap.load(frame.popRef())).getExtra();
          try {
            InputStream is = file.getInputStream(entry);
            KClass cls = MethodArea.findClass("java/io/NativeInputStream");
            if (cls == null) {
              cls = frame.method.clazz.classLoader.loadClass("java/io/NativeInputStream");
            }
            Long obj = cls.newObject();
            Heap.load(obj).setExtra(is);
            frame.pushRef(obj);
          } catch (IOException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("ioe");
          }
        });

    MethodArea.registerMethod("java/util/zip/ZipFile_close_()V", frame -> {
      ZipFile file = (ZipFile) ((KObject) Heap.load(frame.popRef())).getExtra();
      try {
        file.close();
      } catch (IOException e) {
        e.printStackTrace();
        throw new UnsupportedOperationException("zip file close");
      }
    });
  }
}
