package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.heap.Heap;
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
      boolean exists = new File(path).exists();

      int val = exists ? 1 : 0;
      frame.pushInt(val);
    });

    Heap.registerMethod("java/io/UnixFileSystem_canonicalize0_(Ljava/lang/String;)Ljava/lang/String;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_checkAccess_(Ljava/io/File;I)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/io/UnixFileSystem_getLastModifiedTime_(Ljava/io/File;)J", frame -> {
      throw new UnsupportedOperationException();
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
      throw new UnsupportedOperationException();
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
  }
}
