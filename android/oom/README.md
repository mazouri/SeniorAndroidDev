### OOM是常见的java错误，OOM主要有：
1.OOM fo heapjava.lang:OutOfMemoryError: Java heap space
 此OOM是由于JVM中heap的最大值不满足需要，将设置heap的最大值调高即可。
 
2.OOM for Perm：java.lang:OutOfMemoryError: Java perm space
此OOM是由于JVM中perm的最大值不满足需要，将设置perm的最大值调高即可，参数样例为：-XX:MaxPermSize=512M

3.OOM for GC=>例如：java.lang:OutOfMemoryError: GC overhead limit exceeded
此OOM是由于JVM在GC时，对象过多，导致内存溢出，建议调整GC的策略

4.OOM for native thread created:java.lang.OutOfMemoryError: unable to create new native thread
此OOM是由于进程剩余的空间不足，导致创建进程失败

5.OOM for allocate huge array:Exception in thread "main": java.lang.OutOfMemoryError: Requested array size exceeds VM limit
此类信息表明应用程序（或者被应用程序调用的APIs）试图分配一个大于堆大小的数组

6.OOM for small swap:Exception in thread "main": java.lang.OutOfMemoryError: request <size> bytes for <reason>. Out of swap space?
抛出这类错误，是由于从native堆中分配内存失败，并且堆内存可能接近耗尽

7.OutOfMemoryError thrown while trying to throw OutOfMemoryError; no stack trace available
抛出这类错误，一般是由于方法重复调用、死循环引起，直至内存耗尽
