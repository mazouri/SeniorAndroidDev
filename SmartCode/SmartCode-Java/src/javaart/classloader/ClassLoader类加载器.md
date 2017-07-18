# Classloader 类加载器

[一看你就懂，超详细java中的ClassLoader详解](http://blog.csdn.net/briblue/article/details/54973413)


# Java语言系统自带有三个类加载器

1.启动类加载器（BootStrap ClassLoader）:用于加载<JAVA HOME>/lib/目录下的类库文件

2.扩展类加载器（Extension ClassLoader）：用于加载<JAVA HOME>/lib/目录下的扩展类库文件

3.应用程序类加载器（Application ClassLoader）：也叫系统类加载器，负责加载Classpath下的类库，
  程序中默认使用的是这个加载器（当然自己也可以自定义）
  
## 三种类加载器之间关系

双亲委托：先父亲去加载，只有父亲无法加载时，自己再加载

为什么用双亲委托：如果不用，当用户自定义一个java.lang.Object类并放到ClassPath下时，系统中将存在两个Object,

使用双亲委托，加载Object时都是委派给最顶层的BootStrapClassLoader去加载，保证唯一