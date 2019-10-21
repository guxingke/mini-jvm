# AOT 编译[可选]

用于生成二进制可执行文件

依赖 Graalvm 19.1.1

## 构建
正常 maven package 之后
```bash
./build_native.sh
```
生成 mini-jvm 即正常

## 验证
```bash
./mini-jvm -cp example Hello
```
输出 hello 即正常.
