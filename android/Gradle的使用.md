# Gradle的使用

## 六种依赖的区别？

六种依赖分别是：
- Compile:是对所有的build type以及favlors都会参与编译并且打包到最终的apk文件中
- Provided:Provided是对所有的build type以及favlors只在编译时使用，类似eclipse中的external-libs,只参与编译，不打包到最终apk
- APK:只会打包到apk文件中，而不参与编译，所以不能再代码中直接调用jar中的类或方法，否则在编译时会报错
- Test compile:仅仅是针对单元测试代码的编译编译以及最终打包测试apk时有效，而对正常的debug或者release apk包不起作用
- Debug compile:仅仅针对debug模式的编译和最终的debug apk打包
- 仅仅针对Release 模式的编译和最终的Release apk打包

<img src="http://img.blog.csdn.net/20160322145005207?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center">

## 多个Module依赖同一个jar的解决方案？

http://blog.csdn.net/u013134391/article/details/51538511

方案: 将任意一个Module中的jar依赖为compile files('your jar name')，其他需要依赖的地方改为provided files('your jar name')并且删除compile fileTree(include: ['*.jar'], dir: 'libs)。即可  下面详细介绍为什么这样做以及案例

案例介绍

如 环信Module和自己app的Module都要用到定位sdk

1、在自己app的gradle中以compile引入如：
compile files('libs/AMap_Location_V2.4.1_20160414.jar')

2、在环信的Module的gradle中以provided的方式引入如：
provided files('libs/AMap_Location_V2.4.1_20160414.jar')

3、而且环信的gradle中不能存在compile fileTree(include: ['*.jar'], dir: 'libs')

[Android Studio(Gradle)解决库依赖冲突问题](http://www.mobibrw.com/2016/3777)

[Android Studio 里面的引用第三方库总结，以及compile、provided使用](http://blog.csdn.net/w958796636/article/details/52919582)






