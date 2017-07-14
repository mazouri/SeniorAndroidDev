# Java中创建对象的5种方式

以及这几种方式是否调用了构造函数

[http://www.cnblogs.com/wxd0108/p/5685817.html](http://www.cnblogs.com/wxd0108/p/5685817.html)


使用new关键字	} → 调用了构造函数

使用Class类的newInstance方法	} → 调用了构造函数

使用Constructor类的newInstance方法	} → 调用了构造函数

使用clone方法	} → 没有调用构造函数

使用反序列化	} → 没有调用构造函数

> 除了第1个方法，其他4个方法全都转变为invokevirtual(创建对象的直接方法)，第一个方法转变为两个调用，new和invokespecial(构造函数调用)

[Class.newInstance()与new、Constructor.newInstance()的区别](http://blog.csdn.net/panda1234lee/article/details/9009719)