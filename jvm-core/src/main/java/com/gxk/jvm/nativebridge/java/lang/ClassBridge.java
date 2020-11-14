package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.UnionSlot;
import com.gxk.jvm.rtda.heap.Class;
import com.gxk.jvm.rtda.heap.Field;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Instance;
import com.gxk.jvm.rtda.heap.InstanceArray;
import com.gxk.jvm.rtda.heap.Method;
import com.gxk.jvm.rtda.heap.PrimitiveArray;
import com.gxk.jvm.util.Utils;
import java.util.List;

public abstract class ClassBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Class_registerNatives_()V", (frame) -> {
    });
    Heap.registerMethod("java/lang/Class_getName0_()Ljava/lang/String;", frame -> {
      Instance obj = (Instance) frame.popRef();
      String name = obj.getMetaClass().name;
      Class strClazz = Heap.findClass("java/lang/String");
      Instance nameObj = strClazz.newInstance();

      char[] chars = Utils.replace(name, '/', '.').toCharArray();
      final PrimitiveArray array = PrimitiveArray.charArray(chars.length);
      for (int i = 0; i < chars.length; i++) {
        array.ints[i] = chars[i];
      }

      nameObj.setField("value", "[C", UnionSlot.of(array));
      frame.pushRef(nameObj);
    });
    Heap.registerMethod(
        "java/lang/Class_forName0_(Ljava/lang/String;ZLjava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Class;",
        frame -> {
          frame.popRef();
          frame.popRef();
          Integer init = frame.popInt();
          Instance name = (Instance) frame.popRef();
          String clsName = Utils.replace(Utils.obj2Str(name), '.', '/');
          Class clazz = Heap.findClass(clsName);
          if (clazz == null) {
            clazz = frame.method.clazz.classLoader.loadClass(clsName);
          }
          if (clazz == null) {
            throw new IllegalStateException("class not found " + clsName);
          }

          frame.pushRef(clazz.getRuntimeClass());

          if (init == 1 && !clazz.getStat()) {
            Method cinit = clazz.getClinitMethod();
            if (cinit == null) {
              throw new IllegalStateException();
            }

            Frame newFrame = new Frame(cinit);
            clazz.setStat(1);
            Class finalClass = clazz;
            newFrame.setOnPop(() -> finalClass.setStat(2));
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
      Class cls = ((Instance) frame.popRef()).getMetaClass();
      frame.pushInt(cls.isInterface() ? 1 : 0);
    });
    Heap.registerMethod("java/lang/Class_isArray_()Z", frame -> {
      Class metaClass = ((Instance) frame.popRef()).getMetaClass();
      boolean isArray = metaClass.name.startsWith("[") ? true : false;
      frame.pushInt(isArray ? 1 : 0);
    });
    Heap.registerMethod("java/lang/Class_isPrimitive_()Z", frame -> {
      Class cls = ((Instance) frame.popRef()).getMetaClass();
      boolean isPrimitive = cls.isPrimitive();
      frame.pushInt(isPrimitive ? 1 : 0);
    });
    Heap.registerMethod("java/lang/Class_getSuperclass_()Ljava/lang/Class;", frame -> {
      Class superClass = ((Instance) frame.popRef()).getMetaClass().getSuperClass();
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
      Class cls = ((Instance) frame.popRef()).getMetaClass();
      if (cls.name.startsWith("[")) {
        String name = cls.name.substring(1);
        switch (name) {
          case "C":
            Class ccls = Heap.findClass("java/lang/Character");
            Instance runtimeClass = ccls.getRuntimeClass();
            frame.pushRef(runtimeClass);
            break;
          default:
            throw new UnsupportedOperationException();
        }
      }
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
    Heap.registerMethod("java/lang/Class_getProtectionDomain0_()Ljava/security/ProtectionDomain;",
        frame -> {
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
    Heap.registerMethod("java/lang/Class_getDeclaredFields0_(Z)[Ljava/lang/reflect/Field;",
        frame -> {
          throw new UnsupportedOperationException();
        });
    Heap.registerMethod("java/lang/Class_getDeclaredMethods0_(Z)[Ljava/lang/reflect/Method;",
        frame -> {
          throw new UnsupportedOperationException();
        });
    Heap.registerMethod(
        "java/lang/Class_getDeclaredConstructors0_(Z)[Ljava/lang/reflect/Constructor;", frame -> {
          throw new UnsupportedOperationException();
        });
    Heap.registerMethod("java/lang/Class_getDeclaredClasses0_()[Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_desiredAssertionStatus0_(Ljava/lang/Class;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/Class_getPrimitiveClass_(Ljava/lang/String;)Ljava/lang/Class;",
        (frame) -> {
          final Instance instance = frame.popRef();
          final String val = Utils.obj2Str(instance);
          Class cls = Heap.findClass(val);
          frame.pushRef(cls.getRuntimeClass());
        });
    Heap.registerMethod("java/lang/Class_desiredAssertionStatus_()Z", frame -> {
      java.lang.Object xx = frame.popRef();
      frame.pushInt(1);
    });

    // hack
    Heap.registerMethod("java/lang/Class_getSimpleName_()Ljava/lang/String;", frame -> {
      Class cls = ((Instance) frame.popRef()).getMetaClass();
      int lidx = cls.name.lastIndexOf("/");
      int idx = 0;
      if (lidx > 0) {
        idx = lidx + 1;
      }
      String sn = cls.name.substring(idx);
      Instance obj = Utils.str2Obj(sn, frame.method.clazz.classLoader);
      frame.pushRef(obj);
    });

    Heap.registerMethod("java/lang/Class_getCanonicalName_()Ljava/lang/String;", frame -> {
      Class cls = ((Instance) frame.popRef()).getMetaClass();
      String sn = Utils.replace(cls.name, '/', '.');
      Instance obj = Utils.str2Obj(sn, frame.method.clazz.classLoader);
      frame.pushRef(obj);
    });

    Heap.registerMethod("java/lang/Class_getInterfaces_()[Ljava/lang/Class;", frame -> {
      Instance thisObj = ((Instance) frame.popRef());
      Class cls = (thisObj).getMetaClass();
      if (!cls.interfaceNames.isEmpty() && cls.getInterfaces().isEmpty()) {
        frame.pushRef(thisObj);
        cls.interfaceInit(frame);
        return;
      }
      List<Class> interfaces = cls.getInterfaces();
      Integer count = interfaces.size();
      String name = "[Ljava/lang/Class;";
      Class clazz = Heap.findClass(name);
      if (clazz == null) {
        clazz = new Class(1, name, frame.method.clazz.classLoader, null);
        clazz.setSuperClass(Heap.findClass("java/lang/Object"));
        clazz.setStat(2);
        Heap.registerClass(name, clazz);
      }
      Instance[] objs = new Instance[count];

      for (int i = 0; i < interfaces.size(); i++) {
        objs[i] = interfaces.get(i).getRuntimeClass();
      }

      InstanceArray instanceArray = new InstanceArray(clazz, objs);
      frame.pushRef(instanceArray);
    });

    Heap.registerMethod("java/lang/Class_newInstance_()Ljava/lang/Object;", frame -> {
      Class cls = ((Instance) frame.popRef()).getMetaClass();
      Instance obj = cls.newInstance();
      frame.pushRef(obj);
    });

    Heap.registerMethod(
        "java/lang/Class_getDeclaredField_(Ljava/lang/String;)Ljava/lang/reflect/Field;", frame -> {
          Instance nameObj = (Instance) frame.popRef();
          Instance thisObj = (Instance) frame.popRef();
          String name = Utils.obj2Str(nameObj);
          Field field = thisObj.getMetaClass().getField(name);
          frame.pushRef(null);
        });
  }
}

