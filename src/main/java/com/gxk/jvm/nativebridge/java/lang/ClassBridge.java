package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.util.Utils;
import java.util.List;

public abstract class ClassBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Class_registerNatives_()V", (frame) -> {
    });
    Heap.registerMethod("java/lang/Class_getName0_()Ljava/lang/String;", frame -> {
      KObject obj = (KObject) frame.popRef();
      String name = obj.getMetaClass().name;
      KClass strClazz = Heap.findClass("java/lang/String");
      KObject nameObj = strClazz.newObject();
      char[] chars = name.replaceAll("/", ".").toCharArray();
      Character[] characters = new Character[chars.length];
      for (int i = 0; i < chars.length; i++) {
        characters[i] = chars[i];
      }
      KArray kArray = new KArray(Heap.findClass("java/lang/Character"), characters);
      nameObj.setField("value", "[C", new Slot[]{new Slot(kArray)});
      frame.pushRef(nameObj);
    });
    Heap.registerMethod(
        "java/lang/Class_forName0_(Ljava/lang/String;ZLjava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Class;",
        frame -> {
          frame.popRef();
          frame.popRef();
          Integer init = frame.popInt();
          KObject name = (KObject) frame.popRef();
          String clsName = Utils.obj2Str(name).replace(".", "/");
          KClass clazz = Heap.findClass(clsName);
          if (clazz == null) {
            clazz = frame.method.clazz.classLoader.loadClass(clsName);
          }
          if (clazz == null) {
            throw new IllegalStateException("class not found " + clsName);
          }

          frame.pushRef(clazz.getRuntimeClass());

          if (init == 1 && !clazz.isStaticInit()) {
            KMethod cinit = clazz.getClinitMethod();
            if (cinit == null) {
              throw new IllegalStateException();
            }

            Frame newFrame = new Frame(cinit, frame.thread);
            clazz.setStaticInit(1);
            KClass finalKClass = clazz;
            newFrame.setOnPop(() -> finalKClass.setStaticInit(2));
            frame.thread.pushFrame(newFrame);
          }
        });

    Heap.registerMethod("java/lang/Class_isInstance_(Ljava/lang/Object;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_isAssignableFrom_(Ljava/lang/Class;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_isInterface_()Z", frame -> {
      KClass cls = ((KObject) frame.popRef()).getMetaClass();
      frame.pushInt(cls.isInterface() ? 1 : 0);
    });
    Heap.registerMethod("java/lang/Class_isArray_()Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_isPrimitive_()Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getSuperclass_()Ljava/lang/Class;", frame -> {
      KClass superClass = ((KObject) frame.popRef()).getMetaClass().getSuperClass();
      if (superClass == null) {
        frame.pushRef(null);
        return;
      }
      frame.pushRef(superClass.getRuntimeClass());
    });
    Heap.registerMethod("java/lang/Class_getInterfaces0_()[Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getComponentType_()Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getModifiers_()I", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getSigners_()[Ljava/lang/Object;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_setSigners_([Ljava/lang/Object;)V", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getEnclosingMethod0_()[Ljava/lang/Object;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getDeclaringClass0_()Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getProtectionDomain0_()Ljava/security/ProtectionDomain;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getGenericSignature0_()Ljava/lang/String;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getRawAnnotations_()[B", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getRawTypeAnnotations_()[B", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getConstantPool_()Lsun/reflect/ConstantPool;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getDeclaredFields0_(Z)[Ljava/lang/reflect/Field;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getDeclaredMethods0_(Z)[Ljava/lang/reflect/Method;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getDeclaredConstructors0_(Z)[Ljava/lang/reflect/Constructor;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getDeclaredClasses0_()[Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_desiredAssertionStatus0_(Ljava/lang/Class;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getPrimitiveClass_(Ljava/lang/String;)Ljava/lang/Class;", (frame) -> {
      Character[] values = (Character[]) ((KArray) ((KObject) frame.popRef()).getField("value", "[C").val[0].ref).items;
      char[] v2 = new char[values.length];
      for (int i = 0; i < values.length; i++) {
        v2[i] = values[i];
      }
      String val = new String(v2);
      KClass cls = Heap.findClass(val);
      frame.pushRef(cls.getRuntimeClass());
    });
    Heap.registerMethod("java/lang/Class_desiredAssertionStatus_()Z", frame -> {
      java.lang.Object xx = frame.popRef();
      frame.pushInt(1);
    });

    // hack
    Heap.registerMethod("java/lang/Class_getSimpleName_()Ljava/lang/String;", frame -> {
      KClass cls = ((KObject) frame.popRef()).getMetaClass();
      int lidx = cls.name.lastIndexOf("/");
      int idx = 0;
      if (lidx > 0) {
        idx = lidx + 1;
      }
      String sn = cls.name.substring(idx);
      KObject obj = Utils.str2Obj(sn, frame.method.clazz.classLoader);
      frame.pushRef(obj);
    });

    Heap.registerMethod("java/lang/Class_getCanonicalName_()Ljava/lang/String;", frame -> {
      KClass cls = ((KObject) frame.popRef()).getMetaClass();
      String sn = cls.name.replace("/", ".");
      KObject obj = Utils.str2Obj(sn, frame.method.clazz.classLoader);
      frame.pushRef(obj);
    });

    Heap.registerMethod("java/lang/Class_getInterfaces_()[Ljava/lang/Class;", frame -> {
      KObject thisObj = ((KObject) frame.popRef());
      KClass cls = (thisObj).getMetaClass();
      if (!cls.interfaceNames.isEmpty() && cls.getInterfaces().isEmpty()) {
        frame.pushRef(thisObj);
        cls.interfaceInit(frame);
        return;
      }
      List<KClass> interfaces = cls.getInterfaces();
      Integer count = interfaces.size();
      String name = "[Ljava/lang/Class;";
      KClass clazz = Heap.findClass(name);
      if (clazz == null) {
        clazz = new KClass(1, name, frame.method.clazz.classLoader, null);
        clazz.setSuperClass(Heap.findClass("java/lang/Object"));
        clazz.setStaticInit(2);
        Heap.registerClass(name, clazz);
      }
      KObject[] objs = new KObject[count];

      for (int i = 0; i < interfaces.size(); i++) {
        objs[i] = interfaces.get(i).getRuntimeClass();
      }

      KArray kArray = new KArray(clazz, objs);
      frame.pushRef(kArray);
    });

    Heap.registerMethod("java/lang/Class_newInstance_()Ljava/lang/Object;", frame -> {
      KClass cls = ((KObject) frame.popRef()).getMetaClass();
      KObject obj = cls.newObject();
      frame.pushRef(obj);
    });

    Heap.registerMethod("java/lang/Class_getDeclaredField_(Ljava/lang/String;)Ljava/lang/reflect/Field;", frame -> {
      frame.popRef();
      frame.popRef();
      frame.pushRef(null);
    });

    Heap.registerMethod("java/lang/ThreadLocal_<init>_()V", frame -> {
      frame.popRef();
      System.out.println();
    });
  }
}

