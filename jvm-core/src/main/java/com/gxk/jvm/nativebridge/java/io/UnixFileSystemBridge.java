package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.KClass;
import com.gxk.jvm.rtda.memory.KField;
import com.gxk.jvm.rtda.memory.KObject;
import com.gxk.jvm.util.Utils;

import java.io.File;

public abstract class UnixFileSystemBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/io/UnixFileSystem_initIDs_()V", frame -> {
    });

    MethodArea
        .registerMethod("java/io/UnixFileSystem_getBooleanAttributes0_(Ljava/io/File;)I", frame -> {
          KObject fileObj = Heap.load(frame.popRef());
          Long thisObj = frame.popRef();

          KObject pathObj = Heap
              .load(fileObj.getField("path", "Ljava/lang/String;").val[0].refOffset);
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
    MethodArea.registerMethod(
        "java/io/UnixFileSystem_canonicalize0_(Ljava/lang/String;)Ljava/lang/String;", frame -> {
          throw new UnsupportedOperationException();
        });
    MethodArea.registerMethod("java/io/UnixFileSystem_checkAccess_(Ljava/io/File;I)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea
        .registerMethod("java/io/UnixFileSystem_getLastModifiedTime_(Ljava/io/File;)J", frame -> {
          KObject file = Heap.load(frame.popRef());
          frame.popRef();
          KField path = file.getField("path", "Ljava/lang/String;");
          String pathStr = Utils.obj2Str((Heap.load(path.val[0].refOffset)));
          long lm = new File(pathStr).lastModified();
          frame.pushLong(lm);
        });
    MethodArea.registerMethod("java/io/UnixFileSystem_getLength_(Ljava/io/File;)J", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea
        .registerMethod("java/io/UnixFileSystem_setPermission_(Ljava/io/File;IZZ)Z", frame -> {
          throw new UnsupportedOperationException();
        });
    MethodArea
        .registerMethod("java/io/UnixFileSystem_createFileExclusively_(Ljava/lang/String;)Z",
            frame -> {
              throw new UnsupportedOperationException();
            });
    MethodArea.registerMethod("java/io/UnixFileSystem_delete0_(Ljava/io/File;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea
        .registerMethod("java/io/UnixFileSystem_list_(Ljava/io/File;)[Ljava/lang/String;",
            frame -> {
              KObject file = Heap.load(frame.popRef());
              frame.popRef();
              KField path = file.getField("path", "Ljava/lang/String;");
              String pathStr = Utils.obj2Str(Heap.load(path.val[0].refOffset));
              String[] list = new File(pathStr).list();

              Long[] items = new Long[list.length];
              for (int i = 0; i < list.length; i++) {
                items[i] = Utils.str2Obj(list[i], frame.method.clazz.classLoader);
              }

              String name = "[Ljava/lang/String;";
              KClass clazz = MethodArea.findClass(name);
              if (clazz == null) {
                clazz = new KClass(1, name, frame.method.clazz.classLoader, null);
                clazz.setSuperClass(MethodArea.findClass("java/lang/Object"));
                clazz.setStaticInit(2);
                MethodArea.registerClass(name, clazz);
              }

              Long arr = KArray.newArray(clazz, items);
              frame.pushRef(arr);
            });
    MethodArea.registerMethod("java/io/UnixFileSystem_createDirectory_(Ljava/io/File;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea
        .registerMethod("java/io/UnixFileSystem_rename0_(Ljava/io/File;Ljava/io/File;)Z", frame -> {
          throw new UnsupportedOperationException();
        });
    MethodArea
        .registerMethod("java/io/UnixFileSystem_setLastModifiedTime_(Ljava/io/File;J)Z", frame -> {
          throw new UnsupportedOperationException();
        });
    MethodArea.registerMethod("java/io/UnixFileSystem_setReadOnly_(Ljava/io/File;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea.registerMethod("java/io/UnixFileSystem_getSpace_(Ljava/io/File;I)J", frame -> {
      throw new UnsupportedOperationException();
    });
    // hack
    MethodArea
        .registerMethod("java/io/UnixFileSystem_normalize_(Ljava/lang/String;II)Ljava/lang/String;",
            frame -> {
              frame.popInt();
              frame.popInt();
            });
  }
}
