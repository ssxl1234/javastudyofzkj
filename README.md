# a **JAVA Study Record** of *ZhuKJ*

## 1. classpaht实操详解

> 基本目录结构：
>
> - src/
>   - myjava/
>     - util/
>       - Doload.java
>     - MainTest.java
> - bin/
>
> 批量编译运行：
>
> - javac -d .\bin\ .\src\myjava\util\*.java .\src\myjava\MainTest.java
>
>> 批量编译java文件到指定目录。此时，编译器回根据package自动生成对应目录结构
>
> - java -cp .\bin\ myjava.MainTest
>
>> 运行指定classpath下的主程序
>
> - jar -cvf name.jar -C bin/ .
>
>> 将指定文件中的class文件归档成指定文件名的jar包。
>
> - java -cp name.jar myjava.MainTest
>
>> 运行jar包，指定main函数所在的类。必须与main所在的package一致。JVM会以指定的classpath为起点。按照package的逻辑地址去类路径下对应的目录找类文件并加载。如果指定起点有问题。必会报无法加载主类的错误。
>
> java -cp ./lib/*;./bin/ myjava.MainTest
>> -cp 可以是jar包，文件夹路径。可以指定多个，不同的用“**;**”来分割。通常用来指定lib包。比如你引入了其它人提供的类包。可以建一个/lib文件夹，然后放入*.jar包并添加到类路径。运行时就不会报找不到xx.class的错误。

## 2. JVM类加载器

JVM类加载负责类的装载，任意一个class由其类加载器和其唯一存在与JVM中的class对象确定其在JVM中的唯一性。

### 2.1 JVM的三大内置加载器

> - 根加载器
> 负责加载虚拟机核心类库
> - 扩展类加载器
> 继承自父加载器，用于加载jre\lib\ext中的类库
> - 系统类加载器
> 继承自扩展类加载器。用于加载classpath下的类库资源。如用户指定的第三方jar包等。

### 2.2 自定义类加载器



---

## 3. 类的加载过程

ClassLoader的主要任务就是加载class文件到JVM中。JVM保存类文件的数据结构。并使其分布在对应内存区域
>
### 3.1 class文件加载一般过程
>
> - 加载阶段
>   负责查找和加载类的二进制文件，即.class文件。
> - 链接阶段
>   - 验证
>       验证类文件的正确性，包括版本，魔术因子等
>   - 准备
>       为静态变量分配内存并初始化其为默认值
>   - 解析
>       把类中的符号应用转换回直接引用
> - 初始化阶段
>   为静态变量赋予正确的初始值
>
### 3.2 主动使用和被动使用

JVM定义了6中主动使用类的场景。它们将导致类的初始化。引用类的静态常量不会导致类的初始化。

> 1. new关键字。
> 2. 访问类的静态变量
> 3. 访问类的静态方法
> 4. 反射操作
>
>```java {.line-numbers}
>   class.forName("classname");
>```
>
> 5. 初始化子类回导致父类初始化
> 6. 启动类即main函数所在的类
