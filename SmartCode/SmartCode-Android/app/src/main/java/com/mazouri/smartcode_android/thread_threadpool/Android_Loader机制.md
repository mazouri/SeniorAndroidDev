# Android Loader机制

参考：
[Android Loaders（一）概述](http://blog.csdn.net/liaoqianchuan00/article/details/24094575)
[Android Loaders（二）Loader的使用](http://blog.csdn.net/liaoqianchuan00/article/details/24094733)
[Android Loaders（三）实现一个Base Loader][http://blog.csdn.net/liaoqianchuan00/article/details/24094913]

[Android_Loader_使用LoaderManager管理Loader实现异步动态加载数据](http://blog.csdn.net/zimo2013/article/details/10263339/)
[Android 异步加载神器Loader全解析](http://www.cnblogs.com/punkisnotdead/p/4861376.html)

Android 请求数据更新UI方式：
1.Thread+Handler
2.AsyncTask:
3.Loader机制:

## Loader机制：

- 简单的API让你的Activity/Fragment可以和Loaders进行交互。
- 每个Activity和每个Fragment只有一个LoaderManager的实例，他们不共享这些Loaders。
- 监视数据资源，当内容改变时重新更新
