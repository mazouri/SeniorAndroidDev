> 问题：你常用的设计模式有哪些？如何来重构，优化你的代码？

解题思路：

在开发过程中用到最多的几种设计模式有单例模式、工厂方法模式、观察者模式。

1.单例模式：一般需要保证对象在内存中的唯一性时就用单例模式。例如对数据库操作的SqliteOpenHelper的对象。

详见：[单例模式](https://github.com/mazouri/EasyDesignPatterns/blob/master/Creational-patterns/%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F.md)

2.工厂方法模式：主要是为了创建对象提供过渡接口，以便将创建对象的具体过程屏蔽隔离起来，达到提高灵活性的目的。

详见：[工厂方法模式](https://github.com/mazouri/EasyDesignPatterns/blob/master/Creational-patterns/%E5%B7%A5%E5%8E%82%E6%96%B9%E6%B3%95%E5%92%8C%E6%8A%BD%E8%B1%A1%E5%B7%A5%E5%8E%82%E9%97%AE%E9%A2%98.md)

3.观察者模式：定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都将收到通知并自动改变。

详见：[观察者模式](https://github.com/mazouri/EasyDesignPatterns/blob/master/Behavioral-patterns/%E8%A7%82%E5%AF%9F%E8%80%85%E6%A8%A1%E5%BC%8F.md)

4.外观模式：要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行。

详见：[外观模式](https://github.com/mazouri/EasyDesignPatterns/blob/master/Structural-patterns/%E5%A4%96%E8%A7%82%E6%A8%A1%E5%BC%8F.md)


> 说说MVP，MVC，MVVM架构的不同

解题思路：

[MVP](https://github.com/mazouri/SeniorAndroidDev/blob/master/android/dp_in_android/MVP%E8%AF%A6%E8%A7%A3.md)

[MVVM]()
