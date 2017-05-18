# MVP 详解

链接：http://www.jianshu.com/p/9a6845b26856

## 大纲

[](http://upload-images.jianshu.io/upload_images/2518139-44c9e4182fc1bc06.png)

前言

一、什么是MVP

二、MVX解析

三、MVX与三层架构

四、Android上MVP的几种实现

五、最佳实践

六、进阶与不足

## 前言

分出View、Model、Presenter层，抽取View接口。

未使用新模式的原因：首先，主项目比较混乱，改动起来工作量很大，而工期经常较紧，时间不允许；其次，知道自身对MVP理解还不够，怕掉坑里去；最后，也是最重要的一点，当时的项目不是按功能模块划分的包结构，如果改为MVP那是真的就回不到过去了。

## 什么是 MVP？

定义：全称 Model-View-Presenter。

>关于MVC

要说MVP那就不得不说一说它的前辈——MVC。

MVC（Model-View-Controller，模型-视图-控制器）模式。其主要目的在于促进应用中模型，视图，控制器间的关注的清晰分离。

MVP（Model-View-Presenter，模型-视图-表示器）模式则主要用来隔离UI、UI逻辑和业务逻辑、数据。是MVC模式的一个变种。

它们的基本思想有相通的地方：Controller/Presenter负责逻辑的处理，Model提供数据，View负责显示。

> MVP的细分：按照View和Presenter之间的交互方式以及View本身的职责范围，Martin Folwer将MVP可分为PV（Passive View）和SoC（Supervising Controller）两种模式。

- Passive View:顾名思义，PV（Passive View）是一个被动的View，针对包含其中的UI元素（比如控件）的操作不是由View自身来操作，而交给Presenter来操控。

- Supervising Controller:在SoC（Supervising Controller）模式下，为了降低Presenter的复杂度，将诸如数据绑定和格式化这样简单的UI处理逻辑逻辑转移到View中，这些处理逻辑会体现在View实现的接口中。

> GUI和MVX模式的关系

MVC、MVP、MVVM这些模式，都是为了解决拥有图像界面的程序开发复杂性而产生的模式。

先搞清楚一个顺序，是GUI应用程序的出现导致了MVC的产生。GUI应用程序提供给用户可视化的操作界面，这个界面提供给用户数据和信息。在PC上用户与界面的交互主要依赖（键盘，鼠标等。这些操作会执行一些应用逻辑，应用逻辑（application logic）可能会触发一定的业务逻辑（business logic）使应用程序数据的发生变更，数据的变更自然需要用户界面的同步变更以提供最准确的信息。在开发这类应用程序时，为更好的管理应用程序的复杂性，基于职责分离（Speration of Duties）的思想都会对应用程序进行分层。在开发GUI应用程序的时候，会把管理用户界面的层次称为View，应用程序的数据为Model（注意这里的Model指的是Domain Model，这个应用程序对需要解决的问题的数据抽象，不包含应用的状态，可以简单理解为对象）。Model提供数据操作的接口，执行相应的业务逻辑。有了View和Model的分层，那么问题就来了：View如何同步Model的变更，View和Model之间如何粘合在一起。（所谓的MVX中的X都可以归纳为对这个问题不同的处理方式）

> 为什么需要 MVP?

- 理由1：尽量简单

当你在应用中只使用Model-View时，到最后，你会发现“所有的事物都被连接到一起”。



待续。。。









