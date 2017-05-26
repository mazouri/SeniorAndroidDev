# QAAndroidPro

## 1.Service启动方式

解题思路：

https://github.com/mazouri/SeniorAndroidDev/tree/master/android/service

- 什么是Service
- 哪两种启动方式
- 两种启动方式的区别
- 什么时候使用startService，什么时候使用bindService？
- 两种启动方式对Service生命周期函数影响?
- 对Service的认识，什么时候选择使用service（使用场景）

## 2.Activity生命周期；启动模式

http://blog.csdn.net/qq_26787115/article/details/52556842

> 生命周期：典型情况下的生命周期？（四状态、七周期）异常情况下的生命周期？（系统配置发生改变、资源内存不足）

> 四状态指：运行状态、暂停状态、停止状态、销毁状态

> 启动模式：standard？singleTop？singTask？singleInstance？


## 3.如何自定义View;事件分发机制

> 自定义View：4种自定义View的分类？4中案例？

http://blog.csdn.net/qq_26787115/article/details/53428258

> View的工作流程:measure、layout、draw

http://blog.csdn.net/qq_26787115/article/details/53385645

> 事件分发机制：

http://blog.csdn.net/qq_26787115/article/details/53047914

点击事件的传递规则？（dispatchTouchEvent、onInterceptTouchEvent、onTouchEvent）

Activity对点击事件的分发过程？顶级View对事件的分发过程？View对点击事件的处理？

View的滑动冲突？

## 4.Fragment生命周期

> Fragment有什么好处？Fragment有哪些坑及解决方法？

http://www.jianshu.com/p/d9143a92ad94

> Fragment传递数据？add/show/hide/replace区别及使用？FragmentManager？


## 5.Handler机制

> Handler是什么？为什么要使用？基本用法？Handler原理？

http://www.jianshu.com/p/76b44b1fabcc

> 消息机制工作原理：ThreadLocal的工作原理？消息队列的工作原理？Looper的工作原理？Handler的工作原理？

http://www.jianshu.com/p/48464c1d5788



## 6.２个Activity间怎么传递参数

> 三种方式？Intent、静态变量、setResult

http://www.open-open.com/code/view/1434425514020


## 7.ListView优化

> 四种优化方式：convertView的复用、ViewHolder的使用、使用分段加载、使用分页加载

http://m.blog.csdn.net/article/details?id=51942479

> ListView中图片的优化？

http://blog.csdn.net/u010921385/article/details/52435203

> RecyclerView卡顿优化：卡顿的原因？复杂布局的优化？图片加载的优化？滚动时优化？

http://blog.csdn.net/likuan0214/article/details/51899400

http://blog.csdn.net/likuan0214/article/details/51911873

## 8.AsyncTask
## 9.ScrollView嵌套ListView
## 10.内存泄露
## 11.IPC;AIDL
## 12.屏幕适配
## 13.Bitmap优化
## 14.缓存
## 15.第三方库

> Dagger:什么是Dagger？什么是依赖注入？为什么使用Dagger？怎么使用Dagger？

https://github.com/mazouri/SeniorAndroidDev/blob/master/android/thirdlibrary/dagger/Dagger%E9%97%AE%E9%A2%98.md

> Retrofit、Butterknife注解部分源码解析

http://www.trinea.cn/android/java-annotation-android-open-source-analysis/

> ButterKnife 的实现思路

http://www.itnose.net/detail/6643021.html

> RxJava:什么是rxJava？RxJava中的三个角色？Observable的其他几种创建方法？rxJava的线程控制？rxjava实现类似AsyncTask功能？

https://github.com/mazouri/SeniorAndroidDev/blob/master/android/thirdlibrary/rx/RxJava_RxAndroid%E5%9F%BA%E6%9C%AC%E4%BD%BF%E7%94%A8.md

## 16.设计模式

https://github.com/mazouri/SeniorAndroidDev/blob/master/android/dp_in_android/README.md

> 什么是单例模式？几种实现方式？饿汉、懒汉、DCL、静态内部类、枚举

> 什么是工厂方法模式？简单工厂、工厂方法、抽象工厂的区别？

> 什么是观察者模式？关于Observer和Observable？举一个观察者模式的例子？UML

> 什么是外观模式？举例？

## 17.MVP/MVVM

http://www.cnblogs.com/wytiger/p/5996876.html

> 为什么需要MVC，MVP和MVVM？

> 什么是MVC？各层是干什么的？例子？

> 什么是MVP？各层是干什么的？例子？

> 什么是MVVM？各层是干什么的？例子？

## 18.排序
## 19.链表结构
## 20.线程与线程池
## 21.JNI
