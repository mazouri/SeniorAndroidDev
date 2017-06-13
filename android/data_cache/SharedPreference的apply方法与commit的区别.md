# SharedPreference的apply方法与commit的区别

- commit()方法是api 1中就有的，apply()方法是 api 9中添加的
- commit()方法有返回值，成功就返回true，失败返回false。是同步提交到硬件磁盘的，因此，在多个并发的提交commit的时候，它们会等待正在处理的commit保存到磁盘后再操作，因此会降低效率
- apply()没有返回值。apply()是将修改的数据提交给内存，而后异步真正的提交到硬件磁盘。效率更高。
- 由于在一个进程中，sharedPreference是单实例，一般不会出现并发冲突，如果对提交的结果不关心的话，建议使用apply，当然需要确保提交成功且有后续操作的话，还是需要用commit的。
- commit()、apply()两个方法的操作都是原子性的。
