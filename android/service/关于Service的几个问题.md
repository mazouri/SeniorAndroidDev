## Q1.知道Service吗，它有几种启动方式？
    Service是一个专门在后台处理长时间任务的Android组件，它没有UI。
    当应用程序被挂到后台的时候，问了保证应用某些组件仍然可以工作，Service不是独立的进程，
    也不是独立的线程，它是依赖于应用程序的主线程的，也就是说，在更多时候不建议在Service中编写
    耗时的逻辑和操作，否则会引起ANR。
    它有两种启动方式，startService和bindService。

## Q1.1如果不适合直接在service中做耗时操作，那对于耗时逻辑我们应该怎么处理？
    当我们编写的耗时逻辑，不得不被service来管理的时候，就需要引入IntentService。 --> Q6

## Q2.这两种启动方式的区别?
    1.startService只是启动Service，启动它的组件（如Activity）和Service并没有关联，
    只有当Service调用stopSelf或者其他组件调用stopService服务才会终止。
    2.bindService方法启动Service，其他组件可以通过回调获取Service的代理对象和Service交互，
    而这两方也进行了绑定，当启动方销毁时，Service也会自动进行unBind操作，当发现所有绑定
    都进行了unBind时才会销毁Service

## Q2.1什么时候使用startService，什么时候使用bindService？
    启动状态，主要用于执行后台计算；
    绑定状态，主要用于其它组件和Service的交互

## Q3.两种启动方式对Service生命周期函数影响?

![service_life.png](http://upload-images.jianshu.io/upload_images/2518139-7927bc3ba49d90e2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## Q4.Service的onCreate回调函数可以做耗时的操作吗？
    不可以，因为Service的onCreate是在主线程（ActivityThread）中调用的，耗时操作会阻塞UI。

## Q5.如果需要做耗时的操作，你会怎么做？
    1.线程和Handler方式 
    2.Q1.1使用IntentService --> Q6

## Q6.是否知道IntentService，在什么场景下使用IntentService？
    当我们编写的耗时逻辑，不得不被service来管理的时候，就需要引入IntentService，
    IntentService是继承Service的，那么它包含了Service的全部特性，
    当然也包含service的生命周期，那么与service不同的是，IntentService在执行onCreate操作
    的时候，内部开了一个线程，去你执行你的耗时操作。

## Q6.1IntentService的原理
    IntentService中提供了这么一个方法：
```
protected abstract void onHandleIntent(Intent intent);
```
    这是一个抽象方法，也就是说具体的实现需要被延伸到子类。

    onHandleIntent()方法是什么时候被调用的呢？让我们具体看IntentService的内部实现：
```
private final class ServiceHandler extends Handler {  
    public ServiceHandler(Looper looper) {  
        super(looper);  
    }  
  
    @Override  
    public void handleMessage(Message msg) {  
        onHandleIntent((Intent)msg.obj);  
        stopSelf(msg.arg1);  
    }  
}  
  
public IntentService(String name) {  
    super();  
    mName = name;  
}  
  
public void setIntentRedelivery(boolean enabled) {  
    mRedelivery = enabled;  
}  
  
@Override  
public void onCreate() {  
   
    super.onCreate();  
    HandlerThread thread = new HandlerThread("IntentService[" + mName + "]");  
    thread.start();  
  
    mServiceLooper = thread.getLooper();  
    mServiceHandler = new ServiceHandler(mServiceLooper);  
}  
  
@Override  
public void onStart(Intent intent, int startId) {  
    Message msg = mServiceHandler.obtainMessage();  
    msg.arg1 = startId;  
    msg.obj = intent;  
    mServiceHandler.sendMessage(msg);  
}  
```
    IntentService在执行onCreate的方法的时候，其实开了一个线程HandlerThread,并获得了当前
    线程队列管理的looper，并且在onStart的时候，把消息置入了消息队列:
```
@Override  
public void handleMessage(Message msg) {  
       onHandleIntent((Intent)msg.obj);  
       stopSelf(msg.arg1);  
}  
```
    在消息被handler接受并且回调的时候，执行了onHandlerIntent方法，该方法的实现是子类去做的。
    我们可以得出这样的结论：IntentService是通过Handler looper message的方式实现了一个
    多线程的操作，同时耗时操作也可以被这个线程管理和执行，同时不会产生ANR的情况。

## Q6.2使用intentService与service有什么不同呢？（好处）
    1.直接 创建一个默认的工作线程,该线程执行所有的intent传递给onStartCommand()以区别于
    应用程序的主线程
    2.直接创建一个工作队列,来逐个发送intent给onHandleIntent()
    多线程
    3.当请求完成后自己会调用stopSelf()，所以你就不用调用该方法了
    4.提供的默认实现onBind()返回null，所以也不需要重写这个方法
    5.默认实现的onStartCommand()的目的是将intent插入到工作队列中
    
    我们需要做的就是实现onHandlerIntent()方法，还有，构造函数是必需的,而且必须调用
    超IntentService(字符串) ，因为工作线程的构造函数必须使用一个名称
```
  public HelloIntentService() {
	  super("HelloIntentService");
  }
```
    那么它为什么不用stopself()方法呢?因为handlerMessage方法里当处理完请求后就会调用
    stopself()方法了，外界就不用调用了:
```
@Override  
public void handleMessage(Message msg) {  
       onHandleIntent((Intent)msg.obj);  
       stopSelf(msg.arg1);  
}
```

## Q7.工作场景：如果一个应用要从网络上下载MP3文件，并在Activity上展示进度条，这个Activity要求是可以转屏的。那么在转屏时Actvitiy会重启，如何保证下载的进度条能正确展示进度呢？
    错误方法：
    （1）在转屏前将进度缓存，转屏后再读出来。
    （2）使用android:configChanges设置，让转屏时Activity不销毁和重建
    针对第1个方案，我会继续问他将进度值存在哪里？ 转屏的过程中，我们知道Activity的重建
    算是比较耗时的，会可能会有几百毫秒以上，那么这时候下载线程仍然在工作，进度肯定和保存时的进度
    不一致了，如何处理这个问题呢？
    第2个方案，大家可以自己展开思考，实际的项目中可能会需要额外做一些事情来处理ContentView
    的横竖布局的问题。

    如果使用Service来解决这个问题，看似是比较完美的，不过就会涉及Activity（UI）和 Service
    的交互问题

## Q7.1工作场景：如果我们有一个后台服务，是每隔一段时间请求一次服务器，类似于心跳服务，只是没有心跳服务那么频繁，例如每2个小时执行一次连接服务器操作，这样的话，我们的应用可能已经退出了，而我们仍需要这个服务时开启的，如何实现呢？
[链接](http://blog.csdn.net/baicaiye/article/details/53259156)
    
    思路：重写一个IntentService，仿效系统的IntentService，只是让线程执行完毕的时候，
    不再销毁这个Service（也就是删掉源码中的stopSelf语句，那么就不会出现销毁这个Service了），
    那么这样这个Service就能够长时间运行下去，同时不用独立创建AsyncTask和Runable了，同时
    可以直接在onHandleIntent中执行一些长时间的联网操作了

## Q8.对Service的认识，什么时候选择使用service（使用场景）?
    当我们知道了Service的用途，心中有一个Service相关的概念时，针对实际的场景还是要做具体的分析
    再决定是否使用Service。因为Service仍然是在主线程中调用，还是要开线程才能处理长时间的工作，
    Service和UI的交互也让这个方式变得不那么简便。如果你只需要在当前界面去做一些耗时操作，
    界面退出或改变时，工作也要停止，那么这时直接使用Thread（或者AsyncTask, ThreadHandler）
    会更合适你
