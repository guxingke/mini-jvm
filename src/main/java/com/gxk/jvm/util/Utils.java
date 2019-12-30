package com.gxk.jvm.util;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPool;
import com.gxk.jvm.classfile.cp.ClassCp;
import com.gxk.jvm.classfile.cp.FieldDef;
import com.gxk.jvm.classfile.cp.InterfaceMethodDef;
import com.gxk.jvm.classfile.cp.MethodDef;
import com.gxk.jvm.classfile.cp.NameAndType;
import com.gxk.jvm.classfile.cp.Utf8;
import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.rtda.Frame;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Utils {

  public static byte[] readNBytes(DataInputStream stream, int n) throws IOException {
    byte[] bytes = new byte[n];
    for (int i = 0; i < n; i++) {
      bytes[i] = stream.readByte();
    }
    return bytes;
  }

  public static String getString(ConstantPool cp, int index) {
    return ((Utf8) cp.infos[index - 1]).getString();
  }

  public static String getClassName(ConstantPool cp, int classIndex) {
    int nameIndex = ((ClassCp) cp.infos[classIndex - 1]).nameIndex;
    return ((Utf8) cp.infos[nameIndex - 1]).getString();
  }

  public static String getClassNameByMethodDefIdx(ConstantPool constantPool, int mdIdx) {
    ConstantInfo methodInfo = constantPool.infos[mdIdx - 1];
    MethodDef methodDef = (MethodDef) methodInfo;
    return getClassName(constantPool, methodDef.classIndex);
  }

  public static String getClassNameByIMethodDefIdx(ConstantPool constantPool, int mdIdx) {
    ConstantInfo methodInfo = constantPool.infos[mdIdx - 1];
    InterfaceMethodDef methodDef = (InterfaceMethodDef) methodInfo;
    return getClassName(constantPool, methodDef.classIndex);
  }

  public static String getNameByNameAndTypeIdx(ConstantPool cp, int natIdx) {
    int nameIndex = ((NameAndType) cp.infos[natIdx - 1]).nameIndex;
    return getString(cp, nameIndex);
  }

  public static String getTypeByNameAndTypeIdx(ConstantPool cp, int natIdx) {
    int idx = ((NameAndType) cp.infos[natIdx - 1]).descriptionIndex;
    return getString(cp, idx);
  }

  public static String getMethodNameByMethodDefIdx(ConstantPool cp, int mdIdx) {
    ConstantInfo methodInfo = cp.infos[mdIdx - 1];
    MethodDef methodDef = (MethodDef) methodInfo;
    return getNameByNameAndTypeIdx(cp, methodDef.nameAndTypeIndex);
  }

  public static String getMethodNameByIMethodDefIdx(ConstantPool cp, int mdIdx) {
    ConstantInfo methodInfo = cp.infos[mdIdx - 1];
    InterfaceMethodDef methodDef = (InterfaceMethodDef) methodInfo;
    return getNameByNameAndTypeIdx(cp, methodDef.nameAndTypeIndex);
  }

  public static String getMethodTypeByMethodDefIdx(ConstantPool cp, int mdIdx) {
    ConstantInfo methodInfo = cp.infos[mdIdx - 1];
    MethodDef methodDef = (MethodDef) methodInfo;
    return getTypeByNameAndTypeIdx(cp, methodDef.nameAndTypeIndex);
  }

  public static String getMethodTypeByIMethodDefIdx(ConstantPool cp, int mdIdx) {
    ConstantInfo methodInfo = cp.infos[mdIdx - 1];
    InterfaceMethodDef methodDef = (InterfaceMethodDef) methodInfo;
    return getTypeByNameAndTypeIdx(cp, methodDef.nameAndTypeIndex);
  }

  public static String getClassNameByFieldDefIdx(ConstantPool cp, int gsIndex) {
    ConstantInfo methodInfo = cp.infos[gsIndex - 1];
    FieldDef def = (FieldDef) methodInfo;
    return getClassName(cp, def.classIndex);
  }

  public static String getMethodNameByFieldDefIdx(ConstantPool cp, int index) {
    ConstantInfo methodInfo = cp.infos[index - 1];
    FieldDef def = (FieldDef) methodInfo;
    return getNameByNameAndTypeIdx(cp, def.nameAndTypeIndex);
  }

  public static String getMethodTypeByFieldDefIdx(ConstantPool cp, int index) {
    ConstantInfo methodInfo = cp.infos[index - 1];
    FieldDef def = (FieldDef) methodInfo;
    return getTypeByNameAndTypeIdx(cp, def.nameAndTypeIndex);
  }

  public static List<String> parseMethodDescriptor(String descriptor) {
    if (descriptor.startsWith("()")) {
      return new ArrayList<>();
    }

    descriptor = descriptor.substring(descriptor.indexOf("(") + 1, descriptor.indexOf(")"));

    List<Character> base = Arrays.asList('V', 'Z', 'B', 'C', 'S', 'I', 'J', 'F', 'D');
    List<String> rets = new ArrayList<>();
    for (int i = 0; i < descriptor.length(); i++) {
      if (base.contains(descriptor.charAt(i))) {
        rets.add(String.valueOf(descriptor.charAt(i)));
        continue;
      }
      // array
      if (descriptor.charAt(i) == '[') {
        int temp = i;
        i++;
        while (descriptor.charAt(i) == '[') {
          i++;
        }
        if (base.contains(descriptor.charAt(i))) {
          rets.add(descriptor.substring(temp, i+1));
          continue;
        }
        int idx = descriptor.indexOf(';', i);
        rets.add(descriptor.substring(temp, idx));
        i = idx;
        continue;
      }
      // class
      if (descriptor.charAt(i) == 'L') {
        int idx = descriptor.indexOf(';', i);
        rets.add(descriptor.substring(i, idx));
        i = idx;
        continue;
      }
    }
    return rets;
  }

  public static String genNativeMethodKey(KMethod method) {
    return genNativeMethodKey(method.clazz.name, method.name, method.descriptor);
  }

  public static String genNativeMethodKey(String clazz, String name, String descriptor) {
    return String.format("%s_%s_%s", clazz, name, descriptor);
  }

  public static Object pop(Frame frame, String descriptor) {
    switch (descriptor) {
      case "I":
      case "B":
      case "C":
      case "S":
      case "Z":
        return frame.popInt();
      case "F":
        return frame.popFloat();
      case "J":
        return frame.popLong();
      case "D":
        return frame.popDouble();
      default:
        return frame.popRef();
    }
  }

  public static int setLocals(Frame frame, int idx, String descriptor, Object val) {
    int ret = 1;
    switch (descriptor) {
      case "I":
      case "B":
      case "C":
      case "S":
      case "Z":
        frame.setInt(idx, (Integer) val);
        break;
      case "J":
        frame.setLong(idx, (Long) val);
        ret++;
        break;
      case "F":
        frame.setFloat(idx, (Float) val);
        break;
      case "D":
        frame.setDouble(idx, (Double) val);
        ret++;
        break;
      default:
        frame.setRef(idx, val);
        break;
    }
    return ret;
  }

  public static void push(Frame frame, String descriptor, Object obj) {
    switch (descriptor) {
      case "I":
      case "B":
      case "C":
      case "S":
      case "Z":
        frame.pushInt(((Integer) obj));
        break;
      case "J":
        frame.pushLong(((Long) obj));
        break;
      case "F":
        frame.pushFloat(((Float) obj));
        break;
      case "D":
        frame.pushDouble(((Double) obj));
        break;
      default:
        frame.pushRef(obj);
        break;
    }
  }

  public static String obj2Str(KObject name) {
    if (!name.clazz.name.equalsIgnoreCase("java/lang/String")) {
      throw new IllegalStateException();
    }
    Object[] values = ((KArray) name.getField("value", "[C").val[0].ref).items;
    char[] chars = new char[values.length];
    for (int i = 0; i < values.length; i++) {
      chars[i] = (char) values[i];
    }
    return new String(chars);
  }

  public static KObject str2Obj(String str, ClassLoader classLoader) {
    KClass klass = Heap.findClass("java/lang/String");
    KObject object = klass.newObject();
    KField field = object.getField("value", "[C");
    KClass arrClazz = new KClass(1, "[C", classLoader, null);

    char[] chars = str.toCharArray();
    Character[] characters = new Character[chars.length];
    for (int i = 0; i < chars.length; i++) {
      characters[i] = chars[i];
    }
    KArray arr = new KArray(arrClazz, characters);
    field.val = new Slot[]{new Slot(arr)};
    return object;
  }
}
