# EventBus 源码解析

[EventBus 3.0进阶：源码及其设计模式 完全解析](http://www.jianshu.com/p/bda4ed3017ba)


## EventBusBuilder的一些属性
```
//默认地，EventBus会考虑事件的超类，即事件如果继承自超类，那么该超类也会作为事件发送给订阅者。
//如果设置为false，则EventBus只会考虑事件类本身。

boolean eventInheritance = true;

//当订阅方法是以onEvent开头的时候，可以调用该方法来跳过方法名字的验证，订阅这类会保存在List中
List<Class<?>> skipMethodVerificationForClasses;
```

## 注册 register
要想使一个类成为订阅者，那么这个类必须有一个订阅方法，以@Subscribe注解标记的方法，

接着调用register()方法来进行注册。那么我们直接来看EventBus#register()。

```
public void register(Object subscriber) {
    Class<?> subscriberClass = subscriber.getClass();
    List<SubscriberMethod> subscriberMethods = subscriberMethodFinder.findSubscriberMethods(subscriberClass);
    synchronized (this) {
        for (SubscriberMethod subscriberMethod : subscriberMethods) {
            subscribe(subscriber, subscriberMethod);
        }
    }
}
```

先获取了订阅者类的class，接着交给SubscriberMethodFinder.findSubscriberMethods()处理，

返回结果保存在List<SubscriberMethod>中，由此可推测通过上面的方法把订阅方法找出来了，并保存在集合中，

那么我们直接看这个方法，SubscriberMethodFinder#findSubscriberMethods()。

```
List<SubscriberMethod> findSubscriberMethods(Class<?> subscriberClass) {
    //首先从缓存中取出subscriberMethodss，如果有则直接返回该已取得的方法
    List<SubscriberMethod> subscriberMethods = METHOD_CACHE.get(subscriberClass);
    if (subscriberMethods != null) {
        return subscriberMethods;
    }
    //从EventBusBuilder可知，ignoreGenerateIndex一般为false
    if (ignoreGeneratedIndex) {
        subscriberMethods = findUsingReflection(subscriberClass);
    } else {
        subscriberMethods = findUsingInfo(subscriberClass);
    }
    if (subscriberMethods.isEmpty()) {
        throw new EventBusException("Subscriber " + subscriberClass
                + " and its super classes have no public methods with the @Subscribe annotation");
    } else {
        //将获取的subscriberMeyhods放入缓存中
        METHOD_CACHE.put(subscriberClass, subscriberMethods);
        return subscriberMethods;
    }
}
```

在之后的流程中，利用反射的方式，对订阅者类进行扫描，找出订阅方法，并用上面的Map进行保存，我们来看这个方法


# 发送事件

```
EventBus.getDefault().post(new MessageEvent("Hello !....."));`
```






