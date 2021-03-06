# LeakCanary基本工作原理

- RefWatcher.watch() 创建一个 KeyedWeakReference 到要被监控的对象。
- 然后在后台线程检查引用是否被清除，如果没有，调用GC。
- 如果引用还是未被清除，把 heap 内存 dump 到 APP 对应的文件系统中的一个 .hprof 文件中。
- 在另外一个进程中的 HeapAnalyzerService 有一个 HeapAnalyzer 使用HAHA 解析这个文件。
- 得益于唯一的 reference key, HeapAnalyzer 找到 KeyedWeakReference，定位内存泄漏。
- HeapAnalyzer 计算 到 GC roots 的最短强引用路径，并确定是否是泄漏。如果是的话，建立导致泄漏的引用链。
- 引用链传递到 APP 进程中的 DisplayLeakService， 并以通知的形式展示出来。
