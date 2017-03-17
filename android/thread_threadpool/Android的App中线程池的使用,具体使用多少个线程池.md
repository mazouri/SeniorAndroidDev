#Android的App中线程池的使用，具体使用多少个线程池？

[链接](https://www.zhihu.com/question/37804956)

>疑问：一个 App 是会用到多个第三方库，而第三方库都是有自己的线程池的。我们需不需要自己统一定义一个线程池，然后设置给第三方库。如果不需要，那就任由第三方库数目的累加，而导致线程池数目的累加，这样在一个 App 中就会存在多个线程池，这样没什么性能问题吗？(比如AsyncTask中有线程池，UIL，Glide中都有线程池)


这个问题提得非常好，虽然说线程池的使用跟具体场景有关，但是其实有章可循。
在回答这个问题之前，让我们先看一下几个经典的图片加载框架是如何处理的。

####1.Android-Universal-Image-Loader(下面简称UIL)
UIL的线程池是可以让开发者自己配置的，如果没有主动配置，则会使用默认配置，在ImageLoaderConfiguration的内部类Builder中，代码如下:
```
private void initEmptyFieldsWithDefaultValues() {
	if (taskExecutor == null) {
		taskExecutor = DefaultConfigurationFactory
				.createExecutor(threadPoolSize, threadPriority, tasksProcessingType);
	} else {
		customExecutor = true;
	}
	if (taskExecutorForCachedImages == null) {
		taskExecutorForCachedImages = DefaultConfigurationFactory
				.createExecutor(threadPoolSize, threadPriority, tasksProcessingType);
	} else {
		customExecutorForCachedImages = true;
	}
	...
	}
```
其中taskExecutor是加载任务线程池，taskExecutorForCacheImages是缓存处理线程池。其中threadPoolSize和threadPriority的默认值如下:
```
public static final int DEFAULT_THREAD_POOL_SIZE = 3;		
public static final int DEFAULT_THREAD_PRIORITY = Thread.NORM_PRIORITY - 2;
public static final QueueProcessingType DEFAULT_TASK_PROCESSING_TYPE = QueueProcessingType.FIFO;
...
private QueueProcessingType tasksProcessingType = DEFAULT_TASK_PROCESSING_TYPE;
private int threadPoolSize = DEFAULT_THREAD_POOL_SIZE;
private int threadPriority = DEFAULT_THREAD_PRIORITY;
```
可见UIL的加载任务线程池和缓存处理线程池的默认大小都为3，同时默认线程优先级是Thread.NORM_PRIORITY-2,这个优先级已经比较低了，主要是防止它抢占太多CPU时间片，从而影响主线程的执行，线程池的任务处理类型都是FIFO.

####2.Picasso
类似地，Picasso的线程池也可以开发者自己配置，如果没有配置，则使用默认值，在Picasso#Builder#build()中，代码如下:
```
public Picasso build() {

      ...
      if (service == null) {
        service = new PicassoExecutorService();
      }
      ...

      Dispatcher dispatcher = new Dispatcher(context, service, HANDLER, downloader, cache, stats);

      return new Picasso(context, dispatcher, cache, listener, transformer, requestHandlers, stats,
          defaultBitmapConfig, indicatorsEnabled, loggingEnabled);
    }
```
显然，使用了自定义类PicassoExecutorService,该类代码很简单：
```
class PicassoExecutorService extends ThreadPoolExecutor {
  private static final int DEFAULT_THREAD_COUNT = 3;

  PicassoExecutorService() {
    super(DEFAULT_THREAD_COUNT, DEFAULT_THREAD_COUNT, 0, TimeUnit.MILLISECONDS,
        new PriorityBlockingQueue<Runnable>(), new Utils.PicassoThreadFactory());
  }

  ...

  @Override
  public Future<?> submit(Runnable task) {
    PicassoFutureTask ftask = new PicassoFutureTask((BitmapHunter) task);
    execute(ftask);
    return ftask;
  }

  ...
}
```
可见其默认线程池大小为3，工作队列为优先阻塞队列，而Utils.PicassoThreadFactory()调用到了PicassoThreadFactory,该类如下:
```
 static class PicassoThreadFactory implements ThreadFactory {
    @SuppressWarnings("NullableProblems")
    public Thread newThread(Runnable r) {
      return new PicassoThread(r);
    }
  }
```
很简单，就是继承自ThreadFactory的工厂类，而PicassoThread如下:
```
private static class PicassoThread extends Thread {
    public PicassoThread(Runnable r) {
      super(r);
    }

    @Override public void run() {
      Process.setThreadPriority(THREAD_PRIORITY_BACKGROUND);
      super.run();
    }
  }
```
可见，其线程优先级为THREAD_PRIORITY_BACKGROUND。


但是，Picasso真正灵活的地方是可以根据网络状况调节线程池的大小,代码如下:
```
 void adjustThreadCount(NetworkInfo info) {
    if (info == null || !info.isConnectedOrConnecting()) {
      setThreadCount(DEFAULT_THREAD_COUNT);
      return;
    }
    switch (info.getType()) {
      case ConnectivityManager.TYPE_WIFI:
      case ConnectivityManager.TYPE_WIMAX:
      case ConnectivityManager.TYPE_ETHERNET:
        setThreadCount(4);
        break;
      case ConnectivityManager.TYPE_MOBILE:
        switch (info.getSubtype()) {
          case TelephonyManager.NETWORK_TYPE_LTE:  // 4G
          case TelephonyManager.NETWORK_TYPE_HSPAP:
          case TelephonyManager.NETWORK_TYPE_EHRPD:
            setThreadCount(3);
            break;
          case TelephonyManager.NETWORK_TYPE_UMTS: // 3G
          case TelephonyManager.NETWORK_TYPE_CDMA:
          case TelephonyManager.NETWORK_TYPE_EVDO_0:
          case TelephonyManager.NETWORK_TYPE_EVDO_A:
          case TelephonyManager.NETWORK_TYPE_EVDO_B:
            setThreadCount(2);
            break;
          case TelephonyManager.NETWORK_TYPE_GPRS: // 2G
          case TelephonyManager.NETWORK_TYPE_EDGE:
            setThreadCount(1);
            break;
          default:
            setThreadCount(DEFAULT_THREAD_COUNT);
        }
        break;
      default:
        setThreadCount(DEFAULT_THREAD_COUNT);
    }
  }

  private void setThreadCount(int threadCount) {
    setCorePoolSize(threadCount);
    setMaximumPoolSize(threadCount);
  }
```
可见在Wifi下线程数为4,而4G下线程数为3, 3G下为2， 2G下为1，默认状况为3. 这样做的原因是在慢网络下下载速度慢，较小的线程就足以处理。
####3.Glide
Glide线程池也可以配置，下面我们看一下它的默认配置,在GlideBuilder#createGlide()中:
```
 Glide createGlide() {
    if (sourceExecutor == null) {
      sourceExecutor = GlideExecutor.newSourceExecutor();
    }
    if (diskCacheExecutor == null) {
      diskCacheExecutor = GlideExecutor.newDiskCacheExecutor();
    }

    ...

    return new Glide(
        context,
        engine,
        memoryCache,
        bitmapPool,
        arrayPool,
        connectivityMonitorFactory,
        logLevel,
        defaultRequestOptions.lock());
  }
```
类似地，也有两个线程池，其中sourceExecutor用于缓存未命中Glide的加载、解码和转换任务，diskCacheExecutor用于缓存命中时的加载、解码和转换任务，先来看GlideExecutor.newSourceExecutor():
```
public static GlideExecutor newSourceExecutor() {
    return newSourceExecutor(calculateBestThreadCount(), DEFAULT_SOURCE_EXECUTOR_NAME,
        UncaughtThrowableStrategy.DEFAULT);
  }

public static GlideExecutor newSourceExecutor(int threadCount, String name,
      UncaughtThrowableStrategy uncaughtThrowableStrategy) {
    return new GlideExecutor(threadCount, name, uncaughtThrowableStrategy,
        false /*preventNetworkOperations*/, false /*executeSynchronously*/);
  }

GlideExecutor(int poolSize, String name,
      UncaughtThrowableStrategy uncaughtThrowableStrategy, boolean preventNetworkOperations,
      boolean executeSynchronously) {
    super(
        poolSize /*corePoolSize*/,
        poolSize /*maximumPoolSize*/,
        0 /*keepAliveTime*/,
        TimeUnit.MILLISECONDS,
        new PriorityBlockingQueue<Runnable>(),
        new DefaultThreadFactory(name, uncaughtThrowableStrategy, preventNetworkOperations));
    this.executeSynchronously = executeSynchronously;
  }
```
在calculateBestThreadCount()中会根据CPU的数量和Java虚拟机中可用的处理器数量来选择合适的线程数：
```
public static int calculateBestThreadCount() {
    File[] cpus = null;
    try {
      File cpuInfo = new File(CPU_LOCATION);
      final Pattern cpuNamePattern = Pattern.compile(CPU_NAME_REGEX);
      cpus = cpuInfo.listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File file, String s) {
          return cpuNamePattern.matcher(s).matches();
        }
      });
    } catch (Throwable t) {
       ...
    }

    int cpuCount = cpus != null ? cpus.length : 0;
    int availableProcessors = Math.max(1, Runtime.getRuntime().availableProcessors());
    return Math.min(MAXIMUM_AUTOMATIC_THREAD_COUNT, Math.max(availableProcessors, cpuCount));
  }
```
默认值如下:
```
 public static final String DEFAULT_SOURCE_EXECUTOR_NAME = "source";

  public static final String DEFAULT_DISK_CACHE_EXECUTOR_NAME = "disk-cache";

  public static final int DEFAULT_DISK_CACHE_EXECUTOR_THREADS = 1;

  private static final String TAG = "GlideExecutor";
  private static final String CPU_NAME_REGEX = "cpu[0-9]+";
  private static final String CPU_LOCATION = "/sys/devices/system/cpu/";

  private static final int MAXIMUM_AUTOMATIC_THREAD_COUNT = 4;
```
可见，在Glide中，加载缓存未命中的线程池会根据根据CPU的数量和Java虚拟机中可用的处理器数量来选择合适的线程数，但是最多不超过4.
再看GlideExecutor.newDiskCacheExecutor(),代码如下：
```
public static GlideExecutor newDiskCacheExecutor() {
    return newDiskCacheExecutor(DEFAULT_DISK_CACHE_EXECUTOR_THREADS,
        DEFAULT_DISK_CACHE_EXECUTOR_NAME, UncaughtThrowableStrategy.DEFAULT);
  }

```
显然，加载缓存命中的图片的线程池默认大小为1.

至于前面用到的DefaultThreadFactory,与Picasso中的类似，也是使用android.os.Process.THREAD_PRIORITY_BACKGROUND这个线程优先级

####总结
没有对比就没有伤害，通过上面的分析，可以得到如下结论:
1)UIL的线程池处理非常简单粗暴，没有根据CPU数量来选择，也没有根据网络状况的变化进行调整;
2)Picasso的线程池会根据网络状况的变化进行调整，在Wifi下线程数为4,而4G下线程数为3, 3G下为2， 2G下为1，默认状况为3；
3)Glide加载缓存未命中的线程池会根据根据CPU的数量和Java虚拟机中可用的处理器数量来选择合适的线程数，但是最多不超过4;而加载缓存命中的图片的线程池默认大小为1.

聪明如你，到这里一定可以想到，将Picasso和Glide的特性结合起来是一个不错的选择，考虑到它们的线程池都可以自己配置，所以只要写一个自定义的线程池即可。















