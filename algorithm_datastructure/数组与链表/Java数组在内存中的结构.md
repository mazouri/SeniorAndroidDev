# Java数组在内存中的结构

原文：[What does a Java array look like in memory?](http://www.programcreek.com/2013/04/what-does-a-java-array-look-like-in-memory/)

翻译: [Java 数组在内存中的结构](http://blog.csdn.net/renfufei/article/details/15503469)

## 存储内容
Java中的数组存储两类事物
- 原始值：int,char,...
- 引用（对象指针）

当一个对象通过 new 创建,那么将在**堆内存**中分配一段空间,并且返回其引用(指针).

对于数组，也是同样的方式.

## 一维数组
```
int arr[] = new int[3]; 
```
int[] arr 指向 包含3个整数的数组.(假设创建的是包含10个整数的数组,也是同样的用法)
- 分配指定大小的内存,并返回数组的引用

<img src="http://img.blog.csdn.net/20131112153525015?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcmVuZnVmZWk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast">

## 二维数组
二维数组又有什么区别呢?

实际上,在Java中只有一维数组。二维数组本质上也是一维数组,只是数组中的每一个元素都指向了另一个一维数组而已。
> 同样的原理，多维数组也是如此.
```
int[ ][ ] arr = new int[3][ ];  
arr[0] = new int[3];  
arr[1] = new int[5];  
arr[2] = new int[4];  
```

<img src="http://img.blog.csdn.net/20131112153611437?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcmVuZnVmZWk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast">

## 数组分配在内存的哪个区域?
Java中的数组,也是对象(继承Object),因此数组所在的区域和对象是一样的.

我们知道,**JVM**运行时数据区 包括**JVM栈**,**堆内存**,以及**其他区域**.

看下面的关于对象的简单示例,我们一起来了解数组如何分配,以及引用保存在哪里.
```
class A {  
    int x;  
    int y;  
}  
...  
public void m1() {  
    int i = 0;  
    m2();  
}  
public void m2() {  
    A a = new A();  
}  
...  
```
上面的代码片段中,让我们执行 m1()方法看看发生了什么:
- 当 m1 被调用时,一个新的栈帧(Frame-1)被压入JVM栈中,当然,相关的局部变量也在 Frame-1中创建，比如 i;
- 然后 m1调用m2,,又有一个新的栈帧(Frame-2)被压入到JVM栈中;   m2方法在堆内存中创建了A类的一个对象,此对象的引用保存在 Frame-2的局部变量 a 中. 此时,堆内存和栈内存看起来如下所示:
<img src="http://img.blog.csdn.net/20131112153811625?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcmVuZnVmZWk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast">

而 数组(Array)被当做Object处理,因此数组如何保存的,你应该明白了.
