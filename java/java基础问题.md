# java基础问题

1. String类为什么是final的。
> 答：1.安全性：由于String类不能被继承，所以就不会被修改，这就避免了因为继承引起的安全隐患
2.String类在程序中出现的频率比较高，如果为了避免安全隐患，在它每次出现时都用final来修饰，这无疑会降低程序的执行效率，所以干脆直接将其设为final一提高效率

[参考](https://www.cnblogs.com/hellowhy/p/6536590.html)
[知乎问答](https://www.zhihu.com/question/31345592)

> StringBuffer可以看做是可变的字符串

2. HashMap的源码，实现原理，底层结构。

3. 说说你知道的几个Java集合类：list、set、queue、map实现类咯。。。

4. 描述一下ArrayList和LinkedList各自实现和区别

5. Java中的队列都有哪些，有什么区别。

6. 反射中，Class.forName和classloader的区别

7. Java7、Java8的新特性(baidu问的,好BT)

8. Java数组和链表两种结构的操作效率，在哪些情况下(从开头开始，从结尾开始，从中间开始)，哪些操作(插入，查找，删除)的效率高

9. Java内存泄露的问题调查定位：jmap，jstack的使用等等

10. string、stringbuilder、stringbuffer区别

11. hashtable和hashmap的区别

13 .异常的结构，运行时异常和非运行时异常，各举个例子

14. String a= “abc” String b = “abc” String c = new String(“abc”) String d = “ab” + “c” .他们之间用 == 比较的结果

15. String 类的常用方法

16. Java 的引用类型有哪几种

17. 抽象类和接口的区别

18. java的基础类型和字节大小。

19. Hashtable,HashMap,ConcurrentHashMap 底层实现原理与线程安全问题（建议熟悉 jdk 源码，才能从容应答）

20. 如果不让你用Java Jdk提供的工具，你自己实现一个Map，你怎么做。说了好久，说了HashMap源代码，如果我做，就会借鉴HashMap的原理，说了一通HashMap实现

21. Hash冲突怎么办？哪些解决散列冲突的方法？

22. HashMap冲突很厉害，最差性能，你会怎么解决?从O（n）提升到log（n）咯，用二叉排序树的思路说了一通

23. rehash

24. hashCode() 与 equals() 生成算法、方法怎么重写

25.写一个Java的死锁程序

[Java的死锁程序](https://www.cnblogs.com/mudao/p/5867107.html)

> 解决死锁：使用锁时使用相同的顺序
