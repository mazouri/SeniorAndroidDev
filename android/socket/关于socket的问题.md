# 关于socket的问题

## 什么是socket？

Socket是网络通信中的概念，也称为"套接字"。它是一个对TCP/IP协议进行封装的API。

从设计模式层面讲，socket其实是一个门面模式（外观模式），它把复杂的TCP/IP协议族

隐藏在socket接口后面，对于使用者来说，使用socket的一组简单的接口就是全部，让socket

去组织数据，以符合指定的协议。通过socket，我们可以在android平台上通过tcp/ip协议进行开发，

另外，socket不是一种协议，而是api,它属于传输层，主要为了解决数据如何在网络中传输。

## socket应用场景？

即时通信、消息推送

## Socket编程的工作原理

### 生活中的比喻

你要打电话给一个朋友，先拨号，朋友听到电话铃声后提起电话，这时你和你的朋友就建立起了连接，

就可以讲话了。等交流结束，挂断电话结束此次交谈

### TCP方式工作原理

<img src="http://upload-images.jianshu.io/upload_images/4796541-e2a64bd3b74c6068.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240">

先从服务器端说起。服务器端先初始化Socket，然后与端口绑定(bind)，对端口进行监听(listen)，

调用accept阻塞，等待客户端连接。

在这时如果有个客户端初始化一个Socket，然后连接服务器(connect)，如果连接成功，这时客户端与

服务器端的连接就建立了。

客户端发送数据请求，服务器端接收请求并处理请求，然后把回应数据发送给客户端，客户端读取数据，

最后关闭连接，一次交互结束。

### Socket分类

socket分为流套接字和数据报套接字，分别对应于网络传输控制层中的TCP和UDP协议。

TCP协议是面向连接的协议，提供稳定的双向通信功能，TCP连接的建立需要经过“三次握手”才能完成，

为了提供稳定的数据传输功能，其本身提供了超时重传机制，因此具有很高的稳定性；

而UDP是无连接的，提供不稳定的单向通信功能，当然UDP也可以实现双向通信功能。在性能上，UDP具有更好的效率，

其缺点是不保证数据一定能够正确传输，尤其是在网络拥塞的情况下。

具体原理图如下：

<img src="http://upload-images.jianshu.io/upload_images/944365-8df0ed7afe6b32d1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240">








