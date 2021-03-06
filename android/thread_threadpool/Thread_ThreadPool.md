#Android中的线程与线程池

##1. 简述主线程和子线程
线程在Android中是一个很重要的概念，从用途上来说，线程分为主线程和子线程，主线程主要处理和界面相关的事情，而子线程往往用于执行耗时操作。
由于Android的特性，如果在主线程中执行耗时操作那么，就会导致程序无法及时地响应，导致ANR，因此耗时操作必须放在子线程中去执行。（[主线程和子线程更深入的叙述](#5)）

##2. Android有哪些可以扮演线程的角色？
除了Thread本身之外，在Android中可以扮演线程角色的还有很多，比如AsyncTask和IntentService，同时HandlerThread也是一种特殊的线程。
尽管AsyncTask、IntentService以及HandlerThread的表现形式都有别于传统的线程，但是它们的本质仍然是传统的线程。对于AsyncTask来说，它们底层用到了线程池，对于IntentService和HandlerThread来说，它们的底层则直接使用了线程。

##3. 这些线程都有什么不同的特性和使用场景
不同形式的线程虽然都是线程，但是它们仍然具有不同的特性和使用场景。AsyncTask封装了线程池和Handler，它主要是为了方便开发者在子线程中更新UI。
HandlerThread是一种具有消息循环的线程，在它的内部可以使用Handler。
IntentService是一个服务，系统对其进行了封装是其可以更方便地执行后台任务，IntentService内部采用HandlerThread来执行任务，当任务执行完毕后IntentService会自动退出。
从任务执行的角度来看，IntentService的作用很像一个后台线程，但是IntentService是一种服务，它不容易被系统杀死从而可以尽量保证任务的执行，而如果是一个后台线程，由于这个时候进程中没有活动的四大组件，那么这个线程的优先级就会非常低，会很容易被系统杀死，这就是IntentService的优点。

##4. 线程与线程池
在操作系统中，线程是操作系统调度的最小单元，同时线程又是一种受限的系统资源，也就是线程不可能无限制地产生，并且线程的创建和销毁都会有相应的开销。当系统中存在大量的线程时，系统会通过时间片轮转的方式调度每个线程，因此线程不可能做到绝对的并行，除非线程数量小于等于CPU的核心数，一般来说这是不可能的。试想一下，如果在一个进程中频繁地创建和销毁线程，这显然不是高效的做法。
正确的做法是采用线程池，一个线程池中会缓存一定数量的线程，通过线程池就可以避免因为频繁创建和销毁线程所带来的系统开销。
Android中的线程池来源于Java，主要是通过Executor来派生特定类型的线程池，不同种类的线程池又具有各自的特性。（[Android中的线程池]()）

##5. 主线程和子线程更深入的叙述
主线程是指进程所拥有的线程，在Java中默认情况下一个进程只有一个线程，这个线程就是主线程。
主线程主要处理界面交互相关的逻辑，因为用户随时会和界面发生交互，因此主线程在任何时候都必须有较高的响应速度，否则就会产生界面卡顿的感觉。
为了保持较高的响应速度，这就要求主线程中不能执行耗时的任务，这个时候子线程就派上用场了，子线程也可以叫做工作线程，除了主线程以外的线程都是子线程。
Android沿用了Java的线程模型，其中的线程也分为主线程和子线程，其中主线程也叫UI线程，主线程的作用是运行四大组件以及处理它们和用户的交互，而子线程的作用则是执行耗时任务，比如网络请求、I/O操作等，从Android3.0开始系统要求网络访问必须在子线程中进行，否则网络访问将会失败并抛出NetworkOnMainThreadException这个异常，这样做就是为了避免主线程由于被耗时操作所阻塞从而出现ANR现象。

##6. 关于AsyncTask
为了简化在子线程中访问UI的过程，系统提供了AsyncTask，AsyncTask经过几次修改，导致了对于不同的API版本AsyncTask具有不同的表现，尤其是任务的并发执行上。由于这个原因，很多开发者对于AsyncTask的使用上存在误区。（转[AsyncTask.md](./AsyncTask.md)）
















