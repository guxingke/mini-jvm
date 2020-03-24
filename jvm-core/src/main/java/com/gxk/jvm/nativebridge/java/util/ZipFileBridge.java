package com.gxk.jvm.nativebridge.java.util;

import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.util.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public abstract class ZipFileBridge {
  public static void registerNatives0() {
    Heap.registerMethod("java/util/zip/ZipFile_init_(Ljava/lang/String;)V", frame -> {
      KObject path = (KObject) frame.popRef();
      String pathStr = Utils.obj2Str(path);

      ZipFile file;
      try {
        file = new ZipFile(pathStr);
      } catch (IOException e) {
        throw new UnsupportedOperationException("zip file not found");
      }

      ((KObject) frame.popRef()).setExtra(file);
    });
    Heap.registerMethod("java/util/zip/ZipFile_getEntry_(Ljava/lang/String;)Ljava/util/zip/ZipEntry;", frame -> {
      KObject obj = (KObject) frame.popRef();
      ZipFile file = (ZipFile) ((KObject) frame.popRef()).getExtra();
      String entry = Utils.obj2Str(obj);

      ZipEntry zipEntry = file.getEntry(entry);
      if (zipEntry == null) {
        frame.pushRef(null);
        return;
      }

      KClass cls = Heap.findClass("java/util/zip/ZipEntry");
      if (cls == null) {
        cls = frame.method.clazz.classLoader.loadClass("java/util/zip/ZipEntry");
      }
      KObject entryObj = cls.newObject();
      entryObj.setExtra(zipEntry);
      frame.pushRef(entryObj);
    });
    Heap.registerMethod("java/util/zip/ZipFile_getInputStream_(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;", frame -> {
      ZipEntry entry = (ZipEntry) ((KObject) frame.popRef()).getExtra();
      ZipFile file = (ZipFile) ((KObject) frame.popRef()).getExtra();
      try {
        InputStream is = file.getInputStream(entry);
        KClass cls = Heap.findClass("java/io/NativeInputStream");
        if (cls == null) {
          cls = frame.method.clazz.classLoader.loadClass("java/io/NativeInputStream");
       }
        KObject obj = cls.newObject();
        obj.setExtra(is);
        frame.pushRef(obj);
      } catch (IOException e) {
        e.printStackTrace();
        throw new UnsupportedOperationException("ioe");
      }
    });

    Heap.registerMethod("java/util/zip/ZipFile_close_()V", frame -> {
      ZipFile file = (ZipFile) ((KObject) frame.popRef()).getExtra();
      try {
        file.close();
      } catch (IOException e) {
        e.printStackTrace();
        throw new UnsupportedOperationException("zip file close");
      }
    });
  }
}
