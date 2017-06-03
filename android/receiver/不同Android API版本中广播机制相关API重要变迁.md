# 不同Android API版本中广播机制相关API重要变迁

[Android中通过广播方式调起第三方App](https://www.baidufe.com/item/7434007d7e20f114b7fe.html)

## 从Android5.0/API 21开始,粘性广播(Sticky Broadcast)和有序粘性广播过期，以后不再建议使用。

## “静态注册的广播接收器即使app已经退出，主要有相应的广播发出，依然可以接收到”，但此种描述自Android 3.1开始有可能不再成立“

Android 3.1开始系统在Intent与广播相关的flag增加了参数，分别是：
- FLAG_INCLUDE_STOPPED_PACKAGES：包含已经停止的包（停止：即包所在的进程已经退出）
- FLAG_EXCLUDE_STOPPED_PACKAGES：不包含已经停止的包

主要原因如下：

自Android3.1开始，系统本身则增加了对所有app当前是否处于运行状态的跟踪。在发送广播时，不管是什么广播类型，系统默认直接增加了值为FLAG_EXCLUDE_STOPPED_PACKAGES的flag，导致即使是静态注册的广播接收器，对于其所在进程已经退出的app，同样无法接收到广播。

详情参加Android官方文档：http://developer.android.com/about/versions/android-3.1.html#launchcontrols

由此，对于系统广播，由于是系统内部直接发出，无法更改此intent flag值，因此，3.1开始对于静态注册的接收系统广播的BroadcastReceiver，如果App进程已经退出，将不能接收到广播。

但是对于自定义的广播，可以通过复写此flag为FLAG_INCLUDE_STOPPED_PACKAGES，使得静态注册的BroadcastReceiver，即使所在App进程已经退出，也能能接收到广播，并会启动应用进程，但此时的BroadcastReceiver是重新新建的。

```
Intent intent = new Intent();
intent.setAction(BROADCAST_ACTION);
intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
intent.putExtra("name", "mazouri");
sendBroadcast(intent);
```

> 注1：对于动态注册类型的BroadcastReceiver，由于此注册和取消注册实在其他组件（如Activity）中进行，因此，不受此改变影响

> 注2：在3.1以前，相信不少app可能通过静态注册方式监听各种系统广播，以此进行一些业务上的处理（如即时app已经退出，仍然能接收到，可以启动service等..）,3.1后，静态注册接受广播方式的改变，将直接导致此类方案不再可行。于是，通过将Service与App本身设置成不同的进程已经成为实现此类需求的可行替代方案




