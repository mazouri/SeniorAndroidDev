#ForAndroid

类别：设计模式（项目架构）、Activity、Service（AIDL）、Fragment、ContentProvider、BroadcastReceiver、异步任务、
Android启动过程、自定义View（View事件分发）、缓存机制、数据存储（SQLite）、消息处理机制、IPC机制（binder）、
JSON XML、ListView和RecyclerView、OOM、ANR、TCP／UDP／HTTP／Socket、第三方库、适配、图片（Drawable和Bitmap）、
LBS应用（百度、高德）、消息推送、热更新hotfix、RemoteViews、动画、Window和WindowManager、线程与线程池、
JNI和NDK、性能优化、Apk瘦身、java虚拟机

## （yes）1.设计模式问题

1.1 你常用的设计模式有哪些？如何来重构，优化你的代码？

[解题思路](https://github.com/mazouri/SeniorAndroidDev/blob/master/android/dp_in_android/README.md)

1.2 说说MVP，MVC，MVVM架构的不同

解题思路：

[Android MVP 详解（上）](http://www.jianshu.com/p/9a6845b26856)

[Android MVP 详解（下）](http://www.jianshu.com/p/0590f530c617)

[android MVC && MVP && MVVM分析和对比](http://www.cnblogs.com/wytiger/p/5996876.html)

[Android App的设计架构：MVC,MVP,MVVM与架构经验谈](http://www.cnblogs.com/wytiger/p/5305087.html)

[如何构建Android MVVM应用框架](http://www.open-open.com/lib/view/open1487924345584.html)

## 2.Activity问题

2.1 说说Activity生命周期？

（开发艺术）

2.2 Activity的启动模式及使用场景

（开发艺术）

2.3 两个Activity之间怎么传递数据（Serializable和Parcelable）?

（开发艺术）

2.4 IntentFilter的匹配规则

（开发艺术）

2.5 Activity的工作过程

（开发艺术）

2.6 加速启动activity

[加速启动activity](https://github.com/helen-x/AndroidInterview/blob/master/android/%E5%8A%A0%E9%80%9F%E5%90%AF%E5%8A%A8activity.md)

2.7 onSaveInstanceState和onRestoreInstanceState调用的过程和时机

[onSaveInstanceState和onRestoreInstanceState调用的过程和时机](http://www.jianshu.com/p/e279b3137157)

[Activity异常销毁重启时的生命周期和数据的保存](http://www.jianshu.com/p/ffce099eb964)

参考：http://www.jianshu.com/p/5a8e09bcb769

2.8 Activity A调用Activity B时，A调用什么函数？
[Activity A调用Activity B时，A调用什么函数？](http://www.jianshu.com/p/e279b3137157)

2.9 onNewIntent的作用和调用时机？

[onNewIntent的作用和调用时机？](http://www.jianshu.com/p/e279b3137157)

2.10 Activity典型情况下的生命周期分析

（开发艺术）

2.11 Activity异常情况下的生命周期分析

（开发艺术）

2.12 如何退出Activity？如何安全退出已调用多个Activity的Application？

参考：http://www.jianshu.com/p/5a8e09bcb769

2.13 系统上安装了多种浏览器，能否指定某浏览器访问指定页面？

参考：http://www.jianshu.com/p/5a8e09bcb769

2.14 Activity间通过Intent传递数据大小有没有限制？

http://www.jianshu.com/p/72d2e3491346


[activity](http://www.jianshu.com/p/484eb34f84be)

2.14 Android中Task任务栈的分配

参考：http://www.jianshu.com/p/5a8e09bcb769


## （yes）3.Service问题，IntentService

https://github.com/mazouri/SeniorAndroidDev/tree/master/android/service

3.1 Service的工作过程

（开发艺术）

3.2 Service有哪些启动方法，有什么区别，怎样停用Service？yes

http://blog.csdn.net/iispring/article/details/47689819

3.3 Service与Activity怎么实现通信？yes

3.4 Service里面可以弹出 dialog 或 Toast 吗？

参考：http://www.jianshu.com/p/8aa12128a116

3.5 什么时候使用Service?yes

3.6 怎么在启动一个activity时就启动一个service？yes

3.7 Service 与 Thread 的区别？yes

3.8 如何保证 Service 在后台不被 kill？yes

http://www.jianshu.com/p/89f19d67b348

3.9 什么是IntentService？有何优点？yes

3.10 IntentService 与 Service的异同？yes

## （yes）4.Fragment问题

[Android -- Fragment 基本用法、生命周期与细节注意](http://www.jianshu.com/p/1ff18ec1fb6b)

[深入一点 让细节帮你和Fragment更熟络](http://www.jianshu.com/p/acb7705df1a6)

[Android--Fragment你应该明白的一些疑难点](http://www.jianshu.com/p/94bede7d6f46)

4.1 fragment的生命周期

[fragment的生命周期](http://www.jianshu.com/p/e279b3137157)

4.2 fagement和Activity的通信

4.3 fragment有什么特点

4.4 Fragment嵌套多个Fragment会出现bug吗？

参考：http://blog.csdn.net/megatronkings/article/details/51417510

4.5 怎么理解Activity和Fragment的关系？

参考：http://www.jianshu.com/p/8aa12128a116

4.6 Fragment 的 replace 和 add 方法的区别

参考：http://www.jianshu.com/p/72d2e3491346

4.7 Fragment 在你们项目中的使用

参考：http://www.jianshu.com/p/72d2e3491346

4.8 如何切换 fragement,不重新实例化
参考：http://www.jianshu.com/p/72d2e3491346

## （yes）5.ContentProvider问题

5.1 ContentProvider的工作过程

（开发艺术）

## （yes）6.BroadcastReceiver

6.1 BroadcastReceiver的工作过程

（开发艺术）

6.2 一个 app 被杀掉进程后，是否还能收到广播

[ 广播和Service唤醒被杀死的android app](http://blog.csdn.net/lhd201006/article/details/51136854)

6.3 Android引入广播机制的用意？

## 7.(yes)异步任务AsyncTask问题

7.1网络请求是怎么做的异步呢？什么情况下用Handler，什么情况下用AsyncTask

参考：http://www.jianshu.com/p/8aa12128a116

7.2AsyncTask的优缺点？能否同时并发100+AsyncTask呢？

参考： http://www.jianshu.com/p/3a4d25efce46

http://www.jianshu.com/p/89f19d67b348

7.3请介绍下 AsyncTask 的内部实现和适用的场景

http://www.jianshu.com/p/72d2e3491346

7.4 AsyncTask在不同的SDK版本中的区别

http://www.jianshu.com/p/89f19d67b348

## (yes)8.Android启动过程

8.1启动一个程序，可以主界面点击图标进入，也可以从一个程序中跳转过去，二者有什么区别？

参考：http://www.jianshu.com/p/5a8e09bcb769

8.2 Android系统启动过程，App启动过程

http://www.jianshu.com/p/89f19d67b348

## (yes)9.自定义View问题

9.1 自定义view的基本流程

（开发艺术）

9.2 SurfaceView和View的最本质的区别

http://www.jianshu.com/p/89f19d67b348

## (yes)10.缓存机制问题

10.1 说说 LruCache 底层原理

http://www.jianshu.com/p/72d2e3491346

10.2 什么是三级缓存？原理是什么？

（开发艺术）

## (yes)11.数据存储

## (yes)12.消息处理机制

12.1 Handler的工作原理

（开发艺术）

12.2 ThreadLocal的工作原理

（开发艺术）

12.3 使用 Handler 时怎么避免引起内存泄漏？

参考： http://www.jianshu.com/p/8aa12128a116

12.4 子线程发消息到主线程进行更新 UI，除了 handler 和 AsyncTask，还有什么？

参考：http://www.jianshu.com/p/72d2e3491346

12.5 子线程中能不能 new handler？为什么？

参考：http://www.jianshu.com/p/72d2e3491346


## (yes)13.View事件分发
13.1 Android中touch事件的传递机制是怎样的?

参考：http://www.jianshu.com/p/72d2e3491346

13.2 事件分发中的 onTouch 和 onTouchEvent 有什么区别，又该如何使用？

参考：http://www.jianshu.com/p/72d2e3491346

## (yes)14.SQLite
14.1 SQLite支持事务吗? 添加删除如何提高性能?

参考：http://www.jianshu.com/p/72d2e3491346

## (yes)15.AIDL
15.1 AIDL的全称是什么？如何工作？能处理哪些类型的数据？

（开发艺术）

## (yes)16.android5，6，7，8
16.1 各版本新特性

http://www.jianshu.com/p/89f19d67b348

## (yes)17.IPC机制（艺术2）
17.1 Serializable 和 Parcelable 的区别？

17.2 android中进程的优先级？

（开发艺术）

17.3 Binder机制

http://www.jianshu.com/p/89f19d67b348

## (yes)18.热更新hotfix
18.1 热布丁

http://www.jianshu.com/p/89f19d67b348

## (yes)19.JSON XML问题

19.1 Json的介绍

http://www.jianshu.com/p/89f19d67b348

19.2 android中有哪几种解析xml的类,官方推荐哪种？以及它们的原理和区别

http://www.jianshu.com/p/89f19d67b348

## (yes)20.列表视图ListView和RecyclerView问题

20.1 讲讲ListView容易引起性能问题的地方，再说一下你有什么优化方案

[RecyclerView的卡顿优化（一）](http://blog.csdn.net/likuan0214/article/details/51899400)

[RecyclerView 卡顿优化（二）](http://blog.csdn.net/likuan0214/article/details/51911873)

[RecyclerView.Adapter优化了吗？](https://segmentfault.com/a/1190000005717450)

[ RecyclerView全攻略进阶优化](http://blog.csdn.net/u013278099/article/details/49717023)

20.2 Adapter是什么？你所接触过的adapter有那些？

[Android Adapter原理解析](http://blog.csdn.net/luoyingxing/article/details/52701997)

[偷懒新姿势，打造属于RecyclerView的万能适配器Adapter和ViewHolder](http://blog.csdn.net/tellh/article/details/50670458)

20.3 ListView 如何定位到指定位置

[Android入门 在ListView中如何进行精确的定位](http://www.cnblogs.com/xgjblog/p/4228782.html)

[Listview如何定位到指定位置](http://blog.csdn.net/u013182960/article/details/52831640)

20.4 如何在 ScrollView 中如何嵌入 ListView

（开发艺术 滑动冲突）

20.5 ListView 中图片错位的问题是如何产生的

参考：http://www.jianshu.com/p/72d2e3491346

## (yes)21.OOM问题

21.1内存泄露哪几种情况？如何处理？有使用过什么相关的检测工具吗？

http://www.jianshu.com/p/89f19d67b348

21.2 在Android中，怎么节省内存的使用，怎么主动回收内存？

参考：http://www.jianshu.com/p/5a8e09bcb769

## (yes)22.ANR

22.1 ANR排错

http://www.jianshu.com/p/89f19d67b348

## (yes)23.Socket

http://www.jianshu.com/p/39a2639604ae

http://www.jianshu.com/p/1ef22ce15773

[Android Socket通信--UdpClient](http://www.jianshu.com/p/b44885147355)

[Android Socket通信--TcpClient](http://www.jianshu.com/p/338a61ee5b42)

[android socket开发中可能遇到的坑](http://www.jianshu.com/p/45957e180925)

[Android Socket UDP大文件传输](http://www.jianshu.com/p/9ad56c0fd468)

http://www.jianshu.com/p/089fb79e308b

[概念 TCP/IP Socket Http Restful](http://www.jianshu.com/p/d198b3080432)

## (yes)24.TCP／UDP／HTTP

（HTTP书）

## (yes)25.第三方库

25.1常用哪些开源项目，说说最熟悉的一个？

[Dagger相关的问题](https://github.com/mazouri/SeniorAndroidDev/blob/master/android/thirdlibrary/dagger/Dagger%E9%97%AE%E9%A2%98.md)

[rxjava](https://github.com/mazouri/SeniorAndroidDev/tree/master/android/thirdlibrary/rx)

[dagger](https://github.com/mazouri/SeniorAndroidDev/tree/master/android/thirdlibrary/dagger)

25.2 ButterKnife原理

http://www.jianshu.com/p/89f19d67b348

25.3 图片加载库的对比（Glide UML Picasso Fresco）

http://www.jianshu.com/p/78c0a088fc2e

25.4 Glide是怎么实现图片加载的

http://www.jianshu.com/p/78c0a088fc2e

25.5 第三方SDK开发

[第三方SDK开发](https://github.com/mazouri/SeniorAndroidDev/blob/master/android/thirdlibrary/%E7%AC%AC%E4%B8%89%E6%96%B9sdk%E5%BC%80%E5%8F%91.md)

## (yes)26.适配问题

26.1 有遇到过哪些屏幕和资源适配问题？

[最清晰的Android多屏幕适配方案](http://www.it165.net/pro/html/201509/54281.html)

## (yes)27.图片问题（Drawable）
27.1 图片的处理和优化, 图片圆角处理的方式有哪几种？

[Android 完美实现图片圆角和圆形（对实现进行分析）](http://blog.csdn.net/lmj623565791/article/details/24555655)
[Android Xfermode 实战 实现圆形、圆角图片](http://blog.csdn.net/lmj623565791/article/details/42094215)
[ Android BitmapShader 实战 实现圆形、圆角图片](http://blog.csdn.net/lmj623565791/article/details/41967509)

27.2 Bitmap的加载和Cache

（开发艺术）

27.3 DiskLruCache

（开发艺术）

27.4 谈谈你对 Bitmap 的理解, 什么时候应该手动调用 bitmap.recycle()

http://www.jianshu.com/p/72d2e3491346

27.5 如何加载大图，如几百M甚至1G的大图如何加载？

http://www.jianshu.com/p/78c0a088fc2e

## 28.专业领域
### 28.1LBS应用:使用百度或高德地图的SDK遇到过的问题（国内外限制、精确度等）？

[高德地图使用](https://github.com/mazouri/SeniorAndroidDev/blob/master/android/thirdlibrary/%E9%AB%98%E5%BE%B7SDK%E4%BD%BF%E7%94%A8.md)

### 28.1.1定位项目中，如何选取定位方案，如何平衡耗电与实时位置的精度？

http://www.jianshu.com/p/72d2e3491346

### 28.2插件和热修复的原理:插件和热修复框架的原理，模块化开发方式，64K的方法数限问题.

### 28.3消息推送:国内各个消息推送的使用问题？如何保持Service长活？Http长链接实现等

方案1、使用极光和友盟推送。

[友盟推送官方文档](http://dev.umeng.com/push/android/integration)

方案2、使用XMPP协议（Openfire + Spark + Smack）

方案3、使用MQTT协议（更多信息见：http://mqtt.org/）

### 28.4视频直播

### 28.5音乐

28.5.1 如何加载的音乐信息，如何改善其效率

参考：http://www.jianshu.com/p/5a8e09bcb769

## (yes)29.项目经验和职业规划

29.1说说你的亮点，最值得分享的

[Android面试一天一题（吹牛题）](http://www.jianshu.com/p/a354af5cd6d1)

29.2项目中遇到哪些难题，最终你是如何解决的？

[Android面试一天一题（Day 31：Android技术难题解决方案）](http://www.jianshu.com/p/69d9444e2a9a)

29.3你对未来三到五年的职业规划

[职业生涯](http://www.jianshu.com/nb/7675514)

29.4 谈谈你在工作中是怎样解决一个 bug

http://www.jianshu.com/p/72d2e3491346



#更多的

## (yes)30.RemoteViews

（开发艺术）

## (yes)31.动画
31.1 activity切换动画

[activity切换动画](https://github.com/helen-x/AndroidInterview/blob/master/android/activity%E5%88%87%E6%8D%A2%E5%8A%A8%E7%94%BB.md)

31.2 Android 中的动画有哪几类，它们的特点和区别是什么

http://www.jianshu.com/p/72d2e3491346

31.3 属性动画，例如一个 button 从 A 移动到 B 点，B 点还是可以响应点击事件，这个原理是什么？

http://www.jianshu.com/p/72d2e3491346

31.4 属性动画的步骤

http://www.jianshu.com/p/89f19d67b348

## (yes)32.Window和WindowManager

（开发艺术）

## (yes)33.线程与线程池

33.1 对android主线程的运用和理解

参考：http://www.jianshu.com/p/5a8e09bcb769

33.2 线程间同步有哪几种

http://www.jianshu.com/p/78c0a088fc2e

## 34.JNI和NDK

## (yes)35.性能优化

35.1 怎样对 android 进行优化？

http://www.jianshu.com/p/72d2e3491346

http://www.jianshu.com/p/89f19d67b348

##(yes) 36.与服务器交互

36.1 Android与服务器交互的方式中的对称加密和非对称加密是什么?

http://www.jianshu.com/p/72d2e3491346

36.2 怎么考虑数据传输的安全性

http://www.jianshu.com/p/89f19d67b348

## (yes)37.分包

37.1 MultiDex是怎么实现分包的，它的实现原理，多个dex是怎么加载的

http://www.jianshu.com/p/78c0a088fc2e

## (yes)38.Apk瘦身

http://www.jianshu.com/p/78c0a088fc2e

## 其他问题

1.对android虚拟机的理解，包括内存管理机制垃圾回收机制

参考：http://www.jianshu.com/p/5a8e09bcb769

2.Framework工作方式及原理，Activity是如何生成一个view的，机制是什么

参考：http://www.jianshu.com/p/5a8e09bcb769

3.android本身的一些限制，比如apk包大小限制，读取大文件时的时间限制

参考：http://www.jianshu.com/p/5a8e09bcb769

4.启动应用后，改变系统语言，应用的语言会改变么？

参考：http://www.jianshu.com/p/5a8e09bcb769

5.Android程序与Java程序的区别？

参考：http://www.jianshu.com/p/5a8e09bcb769

6.不同工程中的方法是否可以相互调用？

> 可以,列举aidl访问远程服务的例子.

7.Android root机制

8.如何对 Android 应用进行性能分析

参考：http://www.jianshu.com/p/72d2e3491346

9.Android 中如何捕获未捕获的异常

参考：http://www.jianshu.com/p/72d2e3491346

10.请解释下 Android 程序运行时权限与文件系统权限的区别？

参考：http://www.jianshu.com/p/72d2e3491346

11.JVM

11.1JVM 和Dalvik虚拟机的区别?

http://www.jianshu.com/p/89f19d67b348

12.Android中Java和JavaScript交互

http://www.jianshu.com/p/89f19d67b348

## 实现问题

1.Android 应用中验证码登陆都有哪些实现方案

http://www.jianshu.com/p/72d2e3491346

2.andorid 应用第二次登录实现自动登录

http://www.jianshu.com/p/72d2e3491346

3.怎么实现断点续传下载文件

http://blog.csdn.net/wwj_748/article/details/20146869





## 更多问题：
1.ToolBar使用

2.熟悉format里面的%1$s、%1$d吗？

3.项目(app)结构资源文件(res)内mipmap是用来存放什么文件的？

4.多进程架构该如何写？

5.数据该如何保密？有几种解决方案？有什么优缺点？

6.android的内存回收机制

7.系统自带的音视频有什么问题？

8.线程安全

## Java问题

### 注解
java注解：Java注解（Annotation）原理详解：http://blog.csdn.net/lylwo317/article/details/52163304

Java中的注解是如何工作的？：http://www.importnew.com/10294.html

Java学习之注解Annotation实现原理：http://www.cnblogs.com/whoislcj/p/5671622.html

### 反射
java反射：Java反射机制详解：http://www.cnblogs.com/lzq198754/p/5780331.html

### 动态代理
动态代理：十分钟理解Java中的动态代理：http://www.jb51.net/article/86531.htm

