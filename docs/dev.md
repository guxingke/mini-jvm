本地开发

## 环境要求
1. JDK1.8
2. Maven3.3+
3. Graalvm 19.1.1+ (可选)

## 下载源码
```bash
git clone git@github.com:guxingke/mini-jvm.git
```
如果是 fork, 请自行替换

## 构建
```bash
cd mini-jvm
mvn clean package
```

## 简单测试
```bash
./myjava -cp example Hello
```
输出 hello 即为正常

## 导入 IDE
仅推荐 Idea. 
需要安装 Lombok 插件. 

idea -> File -> open 打开即可. 

