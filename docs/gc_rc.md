自动内存管理-引用计数

## 待解决问题
### 何时在堆内分配内存？
new object 时。
new array 时。

### 引用计数何时更新
```java

public static void main(String[] args){
  test(); a2 2 a 1 b 2 c 1 
}

static void test() {
    A a = new A();  a 1
    B b = new B();  b 1
    C c = new C();  c 1
    a.b = b ;       a 1 b 2
    c.a = a ;       a 2 b 2 c 1 

    A a2 = new A();  a2 1 a 2 b 2 c 1
    c.a = a2 ;       a2 2 a 1 b 2 c 1  // a-- a2++
}
```
c.a = a ; 需不需要递归更新 a 的


### 静态域与非静态域是否不同