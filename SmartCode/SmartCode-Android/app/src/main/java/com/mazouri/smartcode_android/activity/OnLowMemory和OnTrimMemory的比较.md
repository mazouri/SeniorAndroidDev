# OnLowMemory和OnTrimMemory的比较

## public void onLowMemory() {}
说明：OnLowMemory是Android提供的API，在系统内存不足，所有后台程序（优先级为background的进程，不是指后台运行的进程）都被杀死时，系统会调用OnLowMemory。

## public void onTrimMemory(int level) {}
说明：OnTrimMemory是Android 4.0之后提供的API，系统会根据不同的内存状态来回调。例如按Home键时就会调用。
作用：释放缓存包括一些文件缓存，图片缓存等 或 一些动态生成动态添加的View，这些动态生成和添加的View且少数情况下才使用到的View，这时候可以被释放。
TRIM_MEMORY_COMPLETE：内存不足，并且该进程在后台进程列表最后一个，马上就要被清理
TRIM_MEMORY_MODERATE：内存不足，并且该进程在后台进程列表的中部。
TRIM_MEMORY_BACKGROUND：内存不足，并且该进程是后台进程。
TRIM_MEMORY_UI_HIDDEN：内存不足，并且该进程的UI已经不可见了。
TRIM_MEMORY_RUNNING_CRITICAL：内存不足(后台进程不足3个)，并且该进程优先级比较高，需要清理内存
TRIM_MEMORY_RUNNING_LOW：内存不足(后台进程不足5个)，并且该进程优先级比较高，需要清理内存
TRIM_MEMORY_RUNNING_MODERATE：内存不足(后台进程超过5个)，并且该进程优先级比较高，需要清理内存


## 注意：OnLowMemory和OnTrimMemory的比较
（1）OnLowMemory被回调时，已经没有后台进程；而onTrimMemory被回调时，还有后台进程。
（2）OnLowMemory是在最后一个后台进程被杀时调用，一般情况是low memory killer 杀进程后触发；而OnTrimMemory的触发更频繁，每次计算进程优先级时，只要满足条件，都会触发。
（3）通过一键清理后，OnLowMemory不会被触发，而OnTrimMemory会被触发一次。