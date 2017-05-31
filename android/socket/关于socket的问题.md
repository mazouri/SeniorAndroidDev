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

## Socket 与 Http 对比

- Socket属于传输层，因为 TCP / IP协议属于传输层，解决的是数据如何在网络中传输的问题
- HTTP协议 属于 应用层，解决的是如何包装数据

由于二者不属于同一层面，所以本来是没有可比性的。但随着发展，默认的Http里封装了下面几层的使用，所以才会出现Socket & HTTP协议的对比：（主要是工作方式的不同）：

- Http：采用 请求—响应 方式
> 即建立网络连接后，当 客户端 向 服务器 发送请求后，服务器端才能向客户端返回数据。
> 可理解为：是客户端有需要才进行通信
- Socket：采用 服务器主动发送数据 的方式
> 即建立网络连接后，服务器可主动发送消息给客户端，而不需要由客户端向服务器发送请求
> 可理解为：是服务器端有需要才进行通信

## Socket使用步骤

Socket可基于TCP或者UDP协议，但TCP更加常用。所以下面的使用步骤 & 实例的Socket将基于TCP协议:

> 关键api: Socket、InputStream、InputStreamReader、BufferedReader、OutputStream

```
// 步骤1：创建客户端 & 服务器的连接

    // 创建Socket对象 & 指定服务端的IP及端口号 
    Socket socket = new Socket("192.168.1.32", 1989);  

    // 判断客户端和服务器是否连接成功  
    socket.isConnected());


// 步骤2：客户端 & 服务器 通信
// 通信包括：客户端 接收服务器的数据 & 发送数据 到 服务器

    <-- 操作1：接收服务器的数据 -->

            // 步骤1：创建输入流对象InputStream
            InputStream is = socket.getInputStream() 

            // 步骤2：创建输入流读取器对象 并传入输入流对象
            // 该对象作用：获取服务器返回的数据
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            // 步骤3：通过输入流读取器对象 接收服务器发送过来的数据
            br.readLine()；


    <-- 操作2：发送数据 到 服务器 -->                  

            // 步骤1：从Socket 获得输出流对象OutputStream
            // 该对象作用：发送数据
            OutputStream outputStream = socket.getOutputStream(); 

            // 步骤2：写入需要发送的数据到输出流对象中
            outputStream.write（（"Carson_Ho"+"\n"）.getBytes("utf-8")）；
            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞

            // 步骤3：发送数据到服务端 
            outputStream.flush();  


// 步骤3：断开客户端 & 服务器 连接

             os.close();
            // 断开 客户端发送到服务器 的连接，即关闭输出流对象OutputStream

            br.close();
            // 断开 服务器发送到客户端 的连接，即关闭输入流读取器对象BufferedReader

            socket.close();
            // 最终关闭整个Socket连接
```


