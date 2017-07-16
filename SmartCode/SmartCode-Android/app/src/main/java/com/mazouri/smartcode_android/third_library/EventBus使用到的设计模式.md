# EventBus使用到设计模式

## 单例模式
```
EventBus.getDefault().register(this);
```
它的实现如下,是双重校验锁单例模式：
```
static volatile EventBus defaultInstance;

public static EventBus getDefault() {
    if (defaultInstance == null) {
        synchronized (EventBus.class) {
            if (defaultInstance == null) {
                defaultInstance = new EventBus();
            }
        }
    }
    return defaultInstance;
}
```
> EventBus的构造函数并不是私有的，（这一点貌似和单例模式违背吧？）其实不然，
> 这是因为我们应用中可以存在多个EventBus，当时通过getDefault()获取的是默认构造好的EventBus，是同一个实例。
> 因此，对于使用默认的EventBus，是单例的。
> 我们可以自己构造不同功能的EventBus。这就用到了构造者模式。

## 建造者模式
这里的建造者是EventBusBuilder，它的一系列方法用于配置EventBus的属性，使用getDefault()方法获取的实例，会有着默认的配置，

上面说过，EventBus的构造方法是公有的，所以我们可以通过给EventBusBuilder设置不同的属性，进而获取有着不同功能的EventBus。

那么，我们通过建造者模式来手动创建一个EventBus，而不是使用getDefault()方法：
```
EventBus eventBus = EventBus.builder()
        .eventInheritance(false)
        .sendNoSubscriberEvent(true)
        .skipMethodVerificationFor(MainActivity.class)
        .build();
```



