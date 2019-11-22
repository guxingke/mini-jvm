# [Mini-jvm](https://jvm.guxingke.com) [![Gitter](https://badges.gitter.im/guxingke/mini-jvm.svg)](https://gitter.im/guxingke/mini-jvm?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
![CircleCI](https://img.shields.io/circleci/build/github/guxingke/mini-jvm/master?style=for-the-badge&token=f20bab2e6e06b66e96f9440f31fa391524a8ed60)![GitHub](https://img.shields.io/github/license/guxingke/mini-jvm?style=for-the-badge)![GitHub commit activity](https://img.shields.io/github/commit-activity/w/guxingke/mini-jvm?style=for-the-badge)![GitHub last commit](https://img.shields.io/github/last-commit/guxingke/mini-jvm?style=for-the-badge)
------
使用 Java 8 实现 JVM

## 动机
1. 尝试了解 JVM 原理, Learning by doing
2. 纸上得来终觉浅, 实践
3. 用简单的代码帮助 Javaer 理解 JVM

## 快速体验 [Macos 用户]

### Hello world
```bash
brew tap guxingke/repo && brew install mini-jvm

cat <<EOF > HelloWorld.java
public class HelloWorld {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("hello");
      return;
    }

    for(int i = 0; i < args.length; i ++) {
      System.out.println(args[i]);
    }
  }
}
EOF

javac HelloWorld.java

# no args
mini-jvm HelloWold
# => hello

# with program args
mini-jvm HelloWold hello mini-jvm
# =>  hello
# =>  mini-jvm

# 输入 mini-jvm -help 了解更多.
```

### 彩蛋 1 , 简单理解 JVM 基于栈的解释器.
```bash
cat <<EOF > Sum10.java
public class Sum10 {
  public static void main(String[] args) {
    int sum = sum10();
    System.out.println(sum);
  }
  
  public static int sum10() {
    int sum = 0;
    for (int i = 1; i <= 10; i++) {
      sum += i;
    }
    return sum;
  }
}
EOF

javac Sum10.java
java Sum10
# => 55

# 生成类汇编语言
# mini-jvm --bc [classfile] [method]
mini-jvm --bc Sum10.class sum10 > sum10.bc

# 解释上一步生成的 sum10.bc
# mini-jvm -- [bytecode file]
mini-jvm -- sum10.bc
# => 55

# ================================
```

## 快速体验 [其他操作系统]
需要自行下载打包. [Dev](https://jvm.guxingke.com/#/dev)

## 规划
- Class 文件解析 90%
- 字节码执行 80%
- 类加载 80%
- 方法调用 80%
- 实例化 90%
- native 方法 50%
- 异常处理 0%
- 多线程 0%
- self-booting 0%

## 局限
1. 不实现 GC

## 变更记录
- Hello World 级别可用

## 参考
- [The Java® Virtual Machine Specification](https://docs.oracle.com/javase/specs/jvms/se8/html/)
- [JVM 字节码从入门到精通](https://juejin.im/book/5c25811a6fb9a049ec6b23ee/)
- [自己动手写Java虚拟机](https://book.douban.com/subject/26802084/)

