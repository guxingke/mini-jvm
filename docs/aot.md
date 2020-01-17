# AOT 编译[可选]

用于生成二进制可执行文件

依赖 Graalvm 19.1.1

## 构建
正常 maven package 之后
```bash
cd jvm-native
./build_native.sh
```
生成 ../bin/jvm 即正常

## 验证
```bash
./bin/jvm -cp example/target/example.jar Hello
```
输出 hello 即正常.
