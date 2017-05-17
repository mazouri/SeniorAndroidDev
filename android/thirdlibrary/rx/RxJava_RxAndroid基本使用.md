## 1.什么是rxJava？为什么使用它？
简单的说，它就是一个实现异步操作的库  。
（好处）随着程序逻辑变得越来越复杂，它依然能够保持简洁。
>观察者模式。
这里举一个我个常用的例子，点击Button后触发OnClickListener
中的onClick()事件。在这个事件中他们的角色分别如下：
观察者：OnClickListener；
被观察者：Button；
订阅（或注册）：setOnClickListener()

## 2.RxJava中的三个角色
- 观察者：Observer；
- 被观察者：Observable；
- 订阅（或注册）：subscribe()。

Observer 即观察者，它决定事件触发的时候将有怎样的行为。
RxJava 观察者的事件回调方法除了普通事件onNext()（相当于onClick()/onEvent()）之外，还定义了两个特殊的事件：onCompleted()和onError()。

Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件。可以使用create()、just(T...)、from(T[])或from(Iterable<? extends T>)来创建一个 Observable ，并为它定义事件触发规则。

创建了Observable和Observer之后，再用subscribe()方法将它们联结起来。
```
        //1.创建一个观察者
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                Log.i(TAG, "Completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "Error");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
            }
        };
```
```
        //2.使用Observable.create()创建被观察者
        Observable observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Wrold");
                subscriber.onCompleted();
            }
        });
```
```
        //3.订阅
        observable1.subscribe(observer);
```

## 3.扩展的Subscriber
除了Observer接口之外，RxJava 还内置了一个实现了Observer的抽象：Subscriber。Subscriber对Observer接口进行了一些扩展，但他们的基本使用方式是完全一样的：
```
Subscriber<String> subscriber = new Subscriber<String>() {
  @Override
  public void onNext(String s) {
      Log.d(tag, "Item: " + s);
  }

  @Override
  public void onCompleted() {
      Log.d(tag, "Completed!");
  }

  @Override
  public void onError(Throwable e) {
      Log.d(tag, "Error!");
  }
};
```
不仅基本使用方式一样，实质上，在 RxJava 的 subscribe 过程中，Observer也总是会先被转换成一个Subscriber再使用。

## 4.Observer和Subscriber的区别
onStart() unsubscribe() Subscription

## 5.Observable的其他几种创建方法
1、使用Observable.just()创建被观察者

2、使用Observable.from()创建被观察者

从上面几点总结rxJava的用法是这样的：
```
        Observable.just("Hello", "World")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, s);
                    }
                });
```
可以看到，我们这里只用了onNext(obj)，还有两个重写的onError(error)和onCompleted()并没有用到，这样导致我们多出了几行根本用不到的代码。于是就想能不能只写我们使用到的，其他几个没用到的就不写，这样的代码看着才舒服。接下来就是使用本次的主角Action来代替Subscriber

## 6.Action
使用Action来代替Subscriber得到的代码是这样的：
```
Observable.just("Hello", "World")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, s);
                    }
                });
```
Action是RxJava 的一个接口，常用的有Action0和Action1.

## 7.Action0与Action1的区别
>Action0： 它只有一个方法 call()，这个方法是无参无返回值的；由于 onCompleted() 方法也是无参无返回值的，因此 Action0 可以被当成一个包装对象，将 onCompleted() 的内容打包起来将自己作为一个参数传入 subscribe() 以实现不完整定义的回调。

>Ation1：它同样只有一个方法 call(T param)，这个方法也无返回值，但有一个参数；与 Action0 同理，由于 onNext(T obj) 和 onError(Throwable error) 也是单参数无返回值的，因此 Action1 可以将 onNext(obj)和 onError(error) 打包起来传入 subscribe() 以实现不完整定义的回调

## 8.如何使用Action？
定义三个对象，分别打包onNext(obj)、onError(error) 、onCompleted()：
```
      Observable observable = Observable.just("Hello", "World");
      //处理onNext()中的内容
      Action1<String> onNextAction = new Action1<String>() {
          @Override
          public void call(String s) {
              Log.i(TAG, s);
          }
      };
      //处理onError()中的内容
      Action1<Throwable> onErrorAction = new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {

          }
      };
      //处理onCompleted()中的内容
      Action0 onCompletedAction = new Action0() {
          @Override
          public void call() {
              Log.i(TAG, "Completed");

          }
      };
```
接下来使用subscribe重载的方法
```
//使用 onNextAction 来定义 onNext()
Observable
     .just("Hello", "World")
     .subscribe(onNextAction);
//使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
Observable
     .just("Hello", "World")
     .subscribe(onNextAction, onErrorAction);
//使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、
// onError() 和 onCompleted()
Observable
    .just("Hello", "World")
    .subscribe(onNextAction, onErrorAction, onCompletedAction);
```
根据实际情况使用以上的方法处理onNext(obj)、onError(error) 、onCompleted()的回调。
## 9.为什么使用Action也能达到使用Subscriber的结果？
subscribe(Action1 onNext)的源码:
```
    public final Subscription subscribe(final Action1<? super T> onNext) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }

        return subscribe(new Subscriber<T>() {

            @Override
            public final void onCompleted() {
                // do nothing
            }

            @Override
            public final void onError(Throwable e) {
                throw new OnErrorNotImplementedException(e);
            }

            @Override
            public final void onNext(T args) {
                onNext.call(args);
            }

        });
    }
```
还以为有多高深，原来就是把Action对象转化成对应的Subscriber对象了。这样就不难理解为什么可以使用Action来代替Subscriber了。

Action的使用为我们减少了不必要的代码，使得写出的代码看上去更加得简洁。

## 10. Func1接口
Func1和Action1相似。Func1 和 Action的区别在于， Func1 包装的是有返回值的方法。

## 11. map的使用
例：得到多个Student对象中的name，保存到nameList中：
```
        Observable.just(student1, student2, student2)
                //使用map进行转换，参数1：转换前的类型，参数2：转换后的类型
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student i) {
                        String name = i.getName();//获取Student对象中的name
                        return name;//返回name
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        nameList.add(s);
                    }
                });
```
可以看到Observable中原来的参数是Student对象，而最后我们需要的是name，这里使用了map来实现这一转换的过程。当然，map可以多次使用:
```
        //多次使用map，想用几个用几个
        Observable.just("Hello", "World")
                .map(new Func1<String, Integer>() {//将String类型的转化为Integer类型的哈希码
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .map(new Func1<Integer, String>() {//将转化后得到的Integer类型的哈希码再转化为String类型
                    @Override
                    public String call(Integer integer) {
                        return integer.intValue() + "";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, s);
                    }
                });
```
## 12. flatMap的使用
flatMap是一个比教难理解的一个转换，在这里先假设一个需求，需要打印多个Student所学的课程。这跟之前获取Student的name又不同了，这里先确定一下关系，一个Student类中只有一个name，而一个Student却有多门课程（Course），Student我们可以理解成这样：
```
    /**
     * 学生类
     */
    class Student {
        private String name;//姓名
        private List<Course> coursesList;//所修的课程
        ...
    }
    /**
     * 课程类
     */
    class  Course {
        private String name;//课程名
        private String id;
        ...
    }
```
如果使用map来实现打印所有学生所修个课程名，实现的代码是这样的：
```
       List<Student> students = new ArrayList<Student>();
        students.add...
        ...

        Action1<List<Course>> action1 = new Action1<List<Course>>() {
            @Override
            public void call(List<Course> courses) {
                //遍历courses，输出cuouses的name
                 for (int i = 0; i < courses.size(); i++){
                    Log.i(TAG, courses.get(i).getName());
                }
            }
        };
        Observable.from(students)
                .map(new Func1<Student, List<Course>>() {
                    @Override
                    public List<Course> call(Student student) {
                        //返回coursesList
                        return student.getCoursesList();
                    }
                })
                .subscribe(action1);
```
可以看到，在Action1中出现了for来循环打印课程名，使用RxJava就是为了剔除这样的嵌套结构，使得整体的逻辑性更强。这时候就可以使用flatMap了，使用flatMap实现的代码是这样的：
```
        List<Student> students = new ArrayList<Student>();
        students.add...
        ...

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCoursesList());
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Log.i(TAG, course.getName());
                    }
                });
```
这样就实现了跟上面代码一样的效果，看起来有点懵？确实，flatMap理解起来有点绕，刚接触flatMap的时候我也是懵逼一个。下面我将flatMap的示意图，希望能帮助理解：

![rxjava_flatmap.png](http://upload-images.jianshu.io/upload_images/2518139-0606e6c005e77179.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

由上图可以看出Student1、Student2经过flatMap后，按顺序依次经历了Observable1、Observable2，分别转化为Course。最后按顺序得到Course1、Course2、Course3、Course4、Course5、Course6，其中1-3由Student1得到，4-6由Student2得到。
结合代码和示意图，是不是对flatMap有了一定的理解。
## 13.其他操作符
[谁来讲讲Rxjava、rxandroid中的操作符的作用?](https://www.zhihu.com/question/32209660)
>filter：集合进行过滤
each：遍历集合
take：取出集合中的前几个
skip：跳过前几个元素
unique：相当于按照数学上的集合处理，去重

## 14.rxJava的线程控制－Scheduler
RxJava在不指定线程的情况下，发起事件和消费事件默认使用当前线程。所以之前的做法：
```
        Observable.just(student1, student2, student2)
                //使用map进行转换，参数1：转换前的类型，参数2：转换后的类型
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student i) {
                        String name = i.getName();//获取Student对象中的name
                        return name;//返回name
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        nameList.add(s);
                    }
                });
```
因为是在主线程中发起的，所以不管中间map的处理还是Action1的执行都是在主线程中进行的。若是map中有耗时的操作，这样会导致主线程拥塞，这并不是我们想看到的。

Scheduler：线程控制器，可以指定每一段代码在什么样的线程中执行。

模拟一个需求：新的线程发起事件，在主线程中消费：
```
    private void rxJavaTest3() {
        Observable.just("Hello", "Word")
                .subscribeOn(Schedulers.newThread())//指定 subscribe() 发生在新的线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, s);
                    }
                });
```
上面用到了subscribeOn()，和observeOn()方法来指定发生的线程和消费的线程:
>
- subscribeOn()：指定subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
- observeOn()：指定Subscriber 所运行在的线程。或者叫做事件消费的线程。

参数Scheduler，RxJava已经为我们提供了一下几个Scheduler:
- Schedulers.immediate()：直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
- Schedulers.newThread()：总是启用新线程，并在新线程执行操作。
- Schedulers.io()： I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
- Schedulers.computation()：计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
- AndroidSchedulers.mainThread()：它指定的操作将在 Android 主线程运行。

## 15.多次切换线程
!!!**observeOn()可以多次使用，可以随意变换线程**

14中只是对事件的发起和消费制定了线程。如果中间有map之类的操作呢？是否可以实现发起的线程在新线程中，map的处理在IO线程，最后的消费在主线程中。
```
        Observable.just("Hello", "Wrold")
                .subscribeOn(Schedulers.newThread())//指定：在新的线程中发起
                .observeOn(Schedulers.io())         //指定：在io线程中处理
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return handleString(s);       //处理数据
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//指定：在主线程中处理
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        show(s);                       //消费事件
                    }
                });
```
可以看到observeOn()被调用了两次，分别指定了map的处理的现场和消费事件show(s)的线程。

>若将observeOn(AndroidSchedulers.mainThread())去掉会怎么样？不为消费事件show(s)指定线程后，show(s)会在那里执行？
其实，observeOn() 指定的是它之后的操作所在的线程。也就是说，map的处理和最后的消费事件show(s)都会在io线程中执行。
**observeOn()可以多次使用，可以随意变换线程**

更多API和线程切换参考：
[Android-RxJava 常见API使用以及线程转换要点](http://www.jianshu.com/p/8060845d2ca8)


## 16.更多
[RxJava + Retrofit完成网络请求](http://www.jianshu.com/p/1fb294ec7e3b)
[RxJava+Retrofit2+RxBinding](http://www.jianshu.com/p/7c554437fd6e)
[]()
