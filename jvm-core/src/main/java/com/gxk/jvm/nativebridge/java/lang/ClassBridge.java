package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.KClass;
import com.gxk.jvm.rtda.memory.KField;
import com.gxk.jvm.rtda.memory.KMethod;
import com.gxk.jvm.rtda.memory.KObject;
import com.gxk.jvm.util.Utils;
import java.util.List;

public abstract class ClassBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/lang/Class_registerNatives_()V", (frame) -> {
    });
    MethodArea.registerMethod("java/lang/Class_getName0_()Ljava/lang/String;", frame -> {
      KObject obj = Heap.load(frame.popRef());
      String name = obj.getMetaClass().name;
      KClass strClazz = MethodArea.findClass("java/lang/String");
      Long nameObj = strClazz.newObject();
      char[] chars = Utils.replace(name, '/', '.').toCharArray();
      Character[] characters = new Character[chars.length];
      for (int i = 0; i < chars.length; i++) {
        characters[i] = chars[i];
      }
      Long arr = KArray.newArray(MethodArea.findClass("java/lang/Character"), characters);
      Heap.load(nameObj).setField("value", "[C", new Slot[]{new Slot(arr)});
      frame.pushRef(nameObj);
    });
    MethodArea.registerMethod(
        "java/lang/Class_forName0_(Ljava/lang/String;ZLjava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Class;",
        frame -> {
          frame.popRef();
          frame.popRef();
          Integer init = frame.popInt();
          KObject name = Heap.load(frame.popRef());
          String clsName = Utils.replace(Utils.obj2Str(name), '.', '/');
          KClass clazz = MethodArea.findClass(clsName);
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

    MethodArea.registerMethod("java/lang/Class_isInstance_(Ljava/lang/Object;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea.registerMethod("java/lang/Class_isAssignableFrom_(Ljava/lang/Class;)Z", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea.registerMethod("java/lang/Class_isInterface_()Z", frame -> {
      KClass cls = Heap.load(frame.popRef()).getMetaClass();
      frame.pushInt(cls.isInterface() ? 1 : 0);
    });
    MethodArea.registerMethod("java/lang/Class_isArray_()Z", frame -> {
      KClass metaClass = Heap.load(frame.popRef()).getMetaClass();
      boolean isArray = metaClass.name.startsWith("[");
      frame.pushInt(isArray ? 1 : 0);
    });
    MethodArea.registerMethod("java/lang/Class_isPrimitive_()Z", frame -> {
      KClass cls = Heap.load(frame.popRef()).getMetaClass();
      boolean isPrimitive = cls.isPrimitive();
      frame.pushInt(isPrimitive ? 1 : 0);
    });
    MethodArea.registerMethod("java/lang/Class_getSuperclass_()Ljava/lang/Class;", frame -> {
      KClass superClass = Heap.load(frame.popRef()).getMetaClass().getSuperClass();
      if (superClass == null) {
        frame.pushRef(null);
        return;
      }
      frame.pushRef(superClass.getRuntimeClass());
    });
    MethodArea.registerMethod("java/lang/Class_getInterfaces0_()[Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea.registerMethod("java/lang/Class_getComponentType_()Ljava/lang/Class;", frame -> {
      KClass cls = Heap.load(frame.popRef()).getMetaClass();
      if (cls.name.startsWith("[")) {
        String name = cls.name.substring(1);
        switch (name) {
          case "C":
            KClass ccls = MethodArea.findClass("java/lang/Character");
            Long runtimeClass = ccls.getRuntimeClass();
            frame.pushRef(runtimeClass);
            break;
          default:
            throw new UnsupportedOperationException();
        }
      }
    });
    MethodArea.registerMethod("java/lang/Class_getModifiers_()I", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea.registerMethod("java/lang/Class_getSigners_()[Ljava/lang/Object;", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea.registerMethod("java/lang/Class_setSigners_([Ljava/lang/Object;)V", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea
        .registerMethod("java/lang/Class_getEnclosingMethod0_()[Ljava/lang/Object;", frame -> {
          throw new UnsupportedOperationException();
        });
    MethodArea.registerMethod("java/lang/Class_getDeclaringClass0_()Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea
        .registerMethod("java/lang/Class_getProtectionDomain0_()Ljava/security/ProtectionDomain;",
            frame -> {
              throw new UnsupportedOperationException();
            });
    MethodArea
        .registerMethod("java/lang/Class_getGenericSignature0_()Ljava/lang/String;", frame -> {
          throw new UnsupportedOperationException();
        });
    MethodArea.registerMethod("java/lang/Class_getRawAnnotations_()[B", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea.registerMethod("java/lang/Class_getRawTypeAnnotations_()[B", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea
        .registerMethod("java/lang/Class_getConstantPool_()Lsun/reflect/ConstantPool;", frame -> {
          throw new UnsupportedOperationException();
        });
    MethodArea.registerMethod("java/lang/Class_getDeclaredFields0_(Z)[Ljava/lang/reflect/Field;",
        frame -> {
          throw new UnsupportedOperationException();
        });
    MethodArea.registerMethod("java/lang/Class_getDeclaredMethods0_(Z)[Ljava/lang/reflect/Method;",
        frame -> {
          throw new UnsupportedOperationException();
        });
    MethodArea.registerMethod(
        "java/lang/Class_getDeclaredConstructors0_(Z)[Ljava/lang/reflect/Constructor;", frame -> {
          throw new UnsupportedOperationException();
        });
    MethodArea.registerMethod("java/lang/Class_getDeclaredClasses0_()[Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    MethodArea
        .registerMethod("java/lang/Class_desiredAssertionStatus0_(Ljava/lang/Class;)Z", frame -> {
          throw new UnsupportedOperationException();
        });
    MethodArea
        .registerMethod("java/lang/Class_getPrimitiveClass_(Ljava/lang/String;)Ljava/lang/Class;",
            (frame) -> {
              Character[] values = (Character[]) ((KArray) (Heap.load(Heap.load(frame.popRef())
                  .getField("value", "[C").val[0].refOffset))).primitiveItems;
              char[] v2 = new char[values.length];
              for (int i = 0; i < values.length; i++) {
                v2[i] = values[i];
              }
              String val = new String(v2);
              KClass cls = MethodArea.findClass(val);
              frame.pushRef(cls.getRuntimeClass());
            });
    MethodArea.registerMethod("java/lang/Class_desiredAssertionStatus_()Z", frame -> {
      java.lang.Object xx = frame.popRef();
      frame.pushInt(1);
    });

    // hack
    MethodArea.registerMethod("java/lang/Class_getSimpleName_()Ljava/lang/String;", frame -> {
      KClass cls = (Heap.load(frame.popRef())).getMetaClass();
      int lidx = cls.name.lastIndexOf("/");
      int idx = 0;
      if (lidx > 0) {
        idx = lidx + 1;
      }
      String sn = cls.name.substring(idx);
      Long obj = Utils.str2Obj(sn, frame.method.clazz.classLoader);
      frame.pushRef(obj);
    });

    MethodArea.registerMethod("java/lang/Class_getCanonicalName_()Ljava/lang/String;", frame -> {
      KClass cls = (Heap.load(frame.popRef()).getMetaClass());
      String sn = Utils.replace(cls.name, '/', '.');
      Long obj = Utils.str2Obj(sn, frame.method.clazz.classLoader);
      frame.pushRef(obj);
    });

    MethodArea.registerMethod("java/lang/Class_getInterfaces_()[Ljava/lang/Class;", frame -> {
      Long thisObj = frame.popRef();
      KClass cls = (Heap.load(thisObj).getMetaClass());
      if (!cls.interfaceNames.isEmpty() && cls.getInterfaces().isEmpty()) {
        frame.pushRef(thisObj);
        cls.interfaceInit(frame);
        return;
      }
      List<KClass> interfaces = cls.getInterfaces();
      Integer count = interfaces.size();
      String name = "[Ljava/lang/Class;";
      KClass clazz = MethodArea.findClass(name);
      if (clazz == null) {
        clazz = new KClass(1, name, frame.method.clazz.classLoader, null);
        clazz.setSuperClass(MethodArea.findClass("java/lang/Object"));
        clazz.setStaticInit(2);
        MethodArea.registerClass(name, clazz);
      }
      Long[] objs = new Long[count];

      for (int i = 0; i < interfaces.size(); i++) {
        objs[i] = interfaces.get(i).getRuntimeClass();
      }

      Long kArray = KArray.newArray(clazz, objs);
      frame.pushRef(kArray);
    });

    MethodArea.registerMethod("java/lang/Class_newInstance_()Ljava/lang/Object;", frame -> {
      KClass cls = (Heap.load(frame.popRef()).getMetaClass());
      Long obj = cls.newObject();
      frame.pushRef(obj);
    });

    MethodArea.registerMethod(
        "java/lang/Class_getDeclaredField_(Ljava/lang/String;)Ljava/lang/reflect/Field;", frame -> {
          KObject nameObj = Heap.load(frame.popRef());
          KObject thisObj = Heap.load(frame.popRef());
          String name = Utils.obj2Str(nameObj);
          KField field = thisObj.getMetaClass().getField(name);
          frame.pushRef(-1L);
        });
  }
}

