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

(增：如果父布局不处理事件，有多个子view，事件将交由哪个view处理？)

(增：如果子View都不处理，最终事件将交由谁处理？-->都不处理交由activity处理（开发艺术p142）)

## 4.Fragment生命周期

> Fragment有什么好处？Fragment有哪些坑及解决方法？

http://www.jianshu.com/p/d9143a92ad94

> Fragment传递数据？add/show/hide/replace区别及使用？FragmentManager？


## 5.Handler机制

> Handler是什么？为什么要使用？基本用法？Handler原理？

http://www.jianshu.com/p/76b44b1fabcc

> 消息机制工作原理：ThreadLocal的工作原理？消息队列的工作原理？Looper的工作原理？Handler的工作原理？

http://www.jianshu.com/p/48464c1d5788

(增：looper阻塞后，有新消息时怎么唤醒looper继续执行的？)

[Android消息机制1-Handler(Java层)](http://gityuan.com/2015/12/26/handler-message-framework/)

[Android消息机制2-Handler(Native层)](http://gityuan.com/2015/12/27/handler-message-native/)

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

> AsyncTask是什么？四个核心方法？ 工作原理？

http://blog.csdn.net/xhbxhbsq/article/details/53610673

## 9.ScrollView嵌套ListView

-->滑动冲突

> 四种方案解决？手动设置ListView高度、使用单个ListView取代ScrollView中所有内容、使用LinearLayout取代ListView、自定义可适应ScrollView的ListView；四种方案的优缺点

http://blog.csdn.net/u010375364/article/details/51911316

> 问题一 ： 嵌套在 ScrollView的 ListVew数据显示不全，我遇到的是最多只显示两条已有的数据?

> 问题二 、打开套有 ListVew的 ScrollView的页面布局 默认 起始位置不是最顶部

http://www.tuicool.com/articles/FzmU32b

## 10.内存泄露

http://www.cnblogs.com/zhaoyanjun/p/5981386.html

> 什么是内存泄漏？为什么会内存泄漏？

> 5种内存泄漏及解决方案？

- 新建线程引起的Activity内存泄漏
- Activity添加监听器造成Activity内存泄漏
- Handler 匿名内部类造成内存溢出？
- AsyncTask造成内存泄漏
- Timer Tasks 造成内存泄漏


## 11.IPC;AIDL

> 什么是IPC？

IPC是Inter-Process Communication的缩写，含义是进程间通信或者跨进程通信，是指两个进程间进行数据交互的一个过程

> IPC方式？使用Bundle、使用文件共享、使用Messenger、使用AIDL

http://blog.csdn.net/qq_26787115/article/details/52863959

> 使用ContentProvider?使用Socket?

http://blog.csdn.net/qq_26787115/article/details/52894230


## 12.屏幕适配

> 如何进行屏幕尺寸匹配？

- 使得布局元素自适应屏幕尺寸（使用RelativeLayout、Linearlayout，使用"wrap_content"、"match_parent"和"weight“）
- 根据屏幕的配置来加载相应的UI布局（layout-large、sw xxxdp，即small width的缩写、屏幕方向（values-land/values-port））
- “图片资源”匹配

- 以某一分辨率为基准，生成所有分辨率对应像素数列表

http://www.jianshu.com/p/ec5a1a30694b

## 13.Bitmap优化

> Bitmap的高效加载：BitmapFactory的decodeFile、decodeResource、decodeByteArray、decodeStream四种加载方法？BitmapFactory.Options缩放图片？

> Bitmap导致的内存溢出解决：质量压缩、尺寸压缩

http://blog.csdn.net/tyk0910/article/details/53462728?utm_source=tuicool&utm_medium=referral

## 14.缓存

> 什么是LruCache？LinkedHashMap作为存储容器，并对各种操作进行计次

http://www.jianshu.com/p/f32e5d435d6c

> Android照片墙完整版，完美结合LruCache和DiskLruCache

http://blog.csdn.net/guolin_blog/article/details/34093441/

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

> 冒泡排序

> 插入排序

http://blog.csdn.net/sysukehan/article/details/52661369

> 选择排序

http://blog.csdn.net/sysukehan/article/details/52661681

> 快速排序

http://blog.csdn.net/sysukehan/article/details/52661880



## 19.链表结构

[List集合和LinkList的讲解](http://blog.csdn.net/androidstarjack/article/details/43763021)

## 20.线程与线程池

> 主线程和子线程?几种线程形态（AsyncTask、HandlerThread、IntentService）线程池？ThreadPoolExecutor

> 常见几种线程池：4个线程池：FixedThreadPool、CachedThreadPool、ScheduledThreadPool、SingleThreadExecutor

http://www.jianshu.com/p/8265dba04f34

## 21.JNI

- 创建接口类:在java中声明native方法
- 编译接口类:编译java源文件得到class文件，然后通过javah命令导出jni头文件
- 配置Android项目的NativeSupport：配置Android.mk：LOCAL_MODULE
- 添加需要的函数：C/C++代码
- 修改主程序：在java中调用


http://www.2cto.com/kf/201409/330892.html
