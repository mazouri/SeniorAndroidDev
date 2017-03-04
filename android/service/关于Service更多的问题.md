## Q1.我们使用service来做哪些事儿？

Service是一个专门在后台处理长时间任务（比如网络查询，处理数据，更新Content Provider，firing Intents，和触发Notification）的Android组件，它没有UI。

## Q1.1这里的后台指的是什么？
    Android的后台就是指，它的运行是完全不依赖UI的，即使Activity被销毁，或者程序被关闭，只要
    进程还在，Service就可以继续运行

## Q1.2既然在Service里也要创建一个子线程，那为什么不直接在Activity里创建呢？
    因为Activity很难对Thread进行控制，当Activity被销毁之后，就没有任何其它的办法可以再重新获
    取到之前创建的子线程的实例。而且在一个Activity中创建的子线程，另一个Activity无法对其进行
    操作。但是Service就不同了，所有的Activity都可以与Service进行关联，然后可以很方便地操作其中
    的方法，即使Activity被销毁了，之后只要重新与Service建立关联，就又能够获取到原有的Service中
    Binder的实例。因此，使用Service来处理后台任务，Activity就可以放心地finish，完全不需要担心
    无法对后台任务进行控制的情况。

## Q2.音乐播放器如何在后台一直播放？
    Service组件的优先级比不活跃的Activity高，这样被系统杀死的概率就降低了。当然，实际上即便运行
    中的Service被杀，但是当系统资源又足够的时候，Service又会被重启。
    当必要的时候Service的优先级可能被提到与前台Activity相同的优先级（通过组件的标签）。这是极端
    的例子，当结束掉Service会直接影响用户体验的时候，比如音乐的播放被打断。
    尽管Service跑起来不需要UI，但是它仍然在UI线程中执行。所以有些耗时的操作你需要放在其他的工作
    线程中，比如Thread和AsynTask类等

## Q3.怎样使创建的Service只由自己的APP操纵
    需要添加权限
```
<service android:enabled=”true” 
           android:name=”.MyService” 
           android:permission=”com.paad.MY_SERVICE_PERMISSION”/>
```

## Q4. onStartCommand返回参数的作用？
    返回的参数是用来决定当系统在Service运行时杀死了Service，资源足够的时候又重启这个Service，
    系统该如何回应的。
    START_STICKY：如果返回的是这个值，当Service任何时候的重启，onStartCommand都会被调用。
    但是注意的一点：传来的intent参数会丢失，也就是null。
    START_NOT_STICKY：当运行时被杀死的时候，如果之前还有start请求未处理（注意是未处理，而
    不是处理中），Service才会被重启，否则服务自动停止
    START_REDELIVER_INTENT：如果Service在运行中被杀死，如果还有请求未被处理，或者在处理中。
    在后者情况，onStartCommand会被调用，传入之前传入的初始状态的Intent，因为它认为还没有
    完全处理好。

    注意：以上行为只有在System kill event的情况下有效，stopSelf和stopService都不会过问
    onStartCommand的返回状态

    下面2个参数是用来判断你的Service是怎么启动的：
    1.START_FLAG_REDELIVERY  对应着START_REDELIVER_INTENT
    2.START_FLAG_RETRY 对应着START_STICKY

## Q5.如何进行绑定service？
    调用bindService。传入一个intent（隐式或者显式）和ServiceConnection的实例
```
Intent bindIntent = new Intent(MyActivity.this, MyMusicService.class);
bindService(bindIntent, mConnection, Context.BIND_AUTO_CREATE);
```
    你还需要去实现一个ServiceConnection，重写它的onServiceConnected和
    onServiceDisconneted方法去获取一个Service实例的引用。
```
// Reference to the service 
private MyMusicService serviceRef;

// Handles the connection between the service and activity 
private ServiceConnection mConnection = new ServiceConnection() { 
        public void onServiceConnected(ComponentName className, 
                                              IBinder service) { 
           // Called when the connection is made. 
           serviceRef = ((MyMusicService.MyBinder)service).getService(); 
        }

        public void onServiceDisconnected(ComponentName className) { 
           // Received when the service unexpectedly disconnects. 
           serviceRef = null; 
        } 
};
```

## Q6.如何创建前台Service？
    Service几乎都是在后台运行的，一直以来它都是默默地做着辛苦的工作。但是Service的系统优先级
    还是比较低的，当系统出现内存不足情况时，就有可能会回收掉正在后台运行的Service。如果你希望
    Service可以一直保持运行状态，而不会由于系统内存不足的原因导致被回收，就可以考虑使用前台
    Service。前台Service和普通Service最大的区别就在于，它会一直有一个正在运行的图标在系统
    的状态栏显示，下拉状态栏后可以看到更加详细的信息，非常类似于通知的效果。当然有时候你也可能
    不仅仅是为了防止Service被回收才使用前台Service，有些项目由于特殊的需求会要求必须使用前台
    Service，比如说墨迹天气，它的Service在后台更新天气数据的同时，还会在系统状态栏一直显示
    当前天气的信息，如下图所示：

![foreground_service.png](http://upload-images.jianshu.io/upload_images/2518139-4487f73f9f03bd21.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

    在进程优先级中，正在运行的Service优先级是老二的位置，仅次与运行在前台的Activity。
    如果你的Service直接可以与用户交互的，那么考虑将其放在前台是必要的。通过startForeground.
    startForeground通常必须指定一个不间断的Notification。这个Notification存在的时间与
    你的跑在前台的Service相同
```
private void startPlayback(String album, String artist) { 
              int NOTIFICATION_ID = 1;

              // Create an Intent that will open the main Activity 
              // if the notification is clicked. 
              Intent intent = new Intent(this, MyActivity.class); 
              PendingIntent pi = PendingIntent.getActivity(this, 1, intent, 0);

              // Set the Notification UI parameters 
              Notification notification = new Notification(R.drawable.icon, 
                “Starting Playback”, System.currentTimeMillis()); 
              notification.setLatestEventInfo(this, album, artist, pi);

              // Set the Notification as ongoing 
              notification.flags = notification.flags | 
                                        Notification.FLAG_ONGOING_EVENT;

              // Move the Service to the Foreground 
              startForeground(NOTIFICATION_ID, notification);  
}
```

    结束前台的Service通过stopForeground,Notification也会被取消:
```
public void pausePlayback() { 
        // Move to the background and remove the Notification 
        stopForeground(true); 
}
```

## Q7.关于远程service。如果将普通Service转换成一个远程Service，在onCreate中执行，还会不会有ANR的情况呢？
    将一个普通的Service转换成远程Service其实非常简单，只需要在注册Service的时候将
    它的android:process属性指定成:remote就可以了
```
    <service  
        android:name="com.example.servicetest.MyService"  
        android:process=":remote" >  
    </service>  
```
    不会导致ANR，因为使用了远程Service后，MyService已经在另外一个进程当中运行了，所以
    只会阻塞该进程中的主线程，并不会影响到当前的应用程序

## Q7.1那既然远程Service这么好用，干脆以后我们把所有的Service都转换成远程Service吧，还省得再开启线程了?

远程Service非但不好用，甚至可以称得上是较为难用。一般情况下如果可以不使用远程Service，
就尽量不要使用它。在使用Start Service方式开启远程service不会崩溃，但是使用bindservice方式会崩溃。这是由于用Bind Service我们会让MainActivity和MyService建立关联，但是目前MyService已经是一个远程Service了，Activity和Service运行在两个不同的进程当中，这时就不能再使用传统的建立关联的方式，程序也就崩溃了。

## Q7.2如何才能让Activity与一个远程Service建立关联呢?
    有办法，使用AIDL来进行跨进程通信
