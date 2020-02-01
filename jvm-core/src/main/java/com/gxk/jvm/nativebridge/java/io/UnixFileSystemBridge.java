package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.util.Utils;

import java.io.File;

public abstract class UnixFileSystemBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/io/UnixFileSystem_initIDs_()V", frame -> {
    });

    Heap.registerMethod("java/io/UnixFileSystem_getBooleanAttributes0_(Ljava/io/File;)I", frame -> {
      KObject fileObj = (KObject) frame.popRef();
      Object thisObj = frame.popRef();

      KObject pathObj = (KObject) fileObj.getField("path", "Ljava/lang/String;").val[0].ref;
      String path = Utils.obj2Str(pathObj);
      File file = new File(path);
      boolean exists = file.exists();
      boolean directory = file.isDirectory();

      int ret = 0;
      if (exists) {
        ret |= 0x01;
      }
      if (directory) {
        ret |= 0x04;
      }
      frame.pushInt(ret);
    });
    Heap.registerMethod("java/io/UnixFileSystem_canonicalize0_(Ljava/lang/String;)Ljava/lang/String;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_checkAccess_(Ljava/io/File;I)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_getLastModifiedTime_(Ljava/io/File;)J", frame -> {
      KObject file = (KObject) frame.popRef();
      frame.popRef();
      KField path = file.getField("path", "Ljava/lang/String;");
      String pathStr = Utils.obj2Str(((KObject) path.val[0].ref));
      long lm = new File(pathStr).lastModified();
      frame.pushLong(lm);
    });
    Heap.registerMethod("java/io/UnixFileSystem_getLength_(Ljava/io/File;)J", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_setPermission_(Ljava/io/File;IZZ)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_createFileExclusively_(Ljava/lang/String;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_delete0_(Ljava/io/File;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_list_(Ljava/io/File;)[Ljava/lang/String;", frame -> {
      KObject file = (KObject) frame.popRef();
      frame.popRef();
      KField path = file.getField("path", "Ljava/lang/String;");
      String pathStr = Utils.obj2Str(((KObject) path.val[0].ref));
      String[] list = new File(pathStr).list();

      KObject[] items = new KObject[list.length];
      for (int i = 0; i < list.length; i++) {
        items[i] = Utils.str2Obj(list[i], frame.method.clazz.classLoader);
      }

      String name = "[Ljava/lang/String;";
      KClass clazz = Heap.findClass(name);
      if (clazz == null) {
        clazz = new KClass(1, name, frame.method.clazz.classLoader, null);
        clazz.setSuperClass(Heap.findClass("java/lang/Object"));
        clazz.setStaticInit(2);
        Heap.registerClass(name, clazz);
      }

      KArray arr = new KArray(clazz, items);
      frame.pushRef(arr);
    });
    Heap.registerMethod("java/io/UnixFileSystem_createDirectory_(Ljava/io/File;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_rename0_(Ljava/io/File;Ljava/io/File;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_setLastModifiedTime_(Ljava/io/File;J)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_setReadOnly_(Ljava/io/File;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_getSpace_(Ljava/io/File;I)J", frame -> {
      throw new UnsupportedOperationException();
    });
    // hack
    Heap.registerMethod("java/io/UnixFileSystem_normalize_(Ljava/lang/String;II)Ljava/lang/String;", frame -> {
      frame.popInt();
      frame.popInt();
    });
  }
}
