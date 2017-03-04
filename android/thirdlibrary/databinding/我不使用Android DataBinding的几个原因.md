[4 Reasons I’m Not Using Android Data Binding](https://medium.com/@Miqubel/4-reasons-im-not-using-android-data-binding-e62127c2650c#.ijq289s5j)

为什么我依然使用ButterKnife?

声明：这篇文章基于个人经验和一些容易被反驳的因素，你可以对我的观点保持怀疑态度并保持自己的见解。

![android databinding.png](http://upload-images.jianshu.io/upload_images/2518139-3c58db35566460f3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##1.专家不推荐使用
**[JakeWharton](https://github.com/JakeWharton)**是ButterKnife的作者，他直接回应了这个问题：[in this GitHub question](https://github.com/JakeWharton/butterknife/issues/269).
>DataBinding可能在大多不重要的案例中很有用，但是它并没有什么创新，当项目复杂度增加时，他就会像其他平台的解决方案一样带来痛苦，比如XAML。该库扩展到了高级案例，因为它强制你在准确的地方绑定你的逻辑。

确实，我同意这里两个观点：
- 它没有扩展好
- 商业逻辑应该属于代码中

##2.它会导致你创建很多“面条式代码”（控制结构复杂，混乱，难以理解，扭曲纠结，一般有没有经验的程序员或者长期频繁修改的复杂程序导致）
一旦我们开始实现复杂的布局，使用DataBinding这种方案将会增加越来越多的复杂性。

首先我们可能面临的问题有：
- 布局中的include 需要你给每个都传数据源
- 或者你可能想在你的include中创建不同的数据源
- 同样的问题出现在ViewStubs 中
- 使用Picasso加载图片时需要你实现一个自定义的databinding adapter。而且你没法模拟或者依赖注入

而且我们可能会做更多复杂的事情，比如：
- 在布局文件中描述逻辑
```
<TextView
   android:text="@{user.lastName}"
   android:layout_width="wrap_content"
   android:layout_height="wrap_content"
   android:visibility="@{user.isAdult ? View.VISIBLE : View.GONE}"/>
```
- 添加Lambda表达式监听接口
```
android:onLongClick="@{(theView) -> presenter.onLongClick(theView, task)}"
```
- 在布局文件中导入类
```
<data>
    <import type="android.view.View"/>
</data>
```
一部分逻辑在代码里，还有一部分逻辑在布局中？这很快将变为噩梦，或者像面条式代码一样混乱。

##3.并没有比ButterKnife提供的更多
ButterKnife有几点多棒的特性：
- 资源绑定
- 视图列表
- 多个监听绑定

资源绑定在我们使用自定义控件创建一些高级的东西的时候尤其有用，我们需要获取Dimens或者Drawables.

当我们有一系列视图来表示同样的行为时，比如一系列EditText或者Buttons,视图列表和多监听绑定会帮我们节省很多代码。

使用DataBinding的话，你将无法享受这些功能。

##4.如果有针对不同屏幕尺寸的不同布局，这将导致你复制数据绑定的代码并保持两个布局都更新。
我的很多项目中，都需要支持API16，但是我们想给+21的用户更高级的UX，有时就得为不同API复制布局。
设计变更导致的布局更新就很烦人了，如果还要同时保持DataBinding的改变，那简直就是痛苦了。


##那为什么你会想要使用DataBinding呢？
###1.我可以开发的更快速
长期来看，开发的快并非一定是好事。当我们开发app时，我们进行的是马拉松，而不是短跑。如果抓住市场的机会比做出一个长期稳定的项目更重要，那么使用DataBinding来快速完成任务是有用的。

###2.它出现了！
没有第三方依赖库是好事儿！如果所在的大项目已经遇到了方法数限制了（Android的65535），那么开发的Leader不会想在加入一个新的库到里面的。

###3.我在使用MVVM模式
如果你想用观察者模式合理的实现MVVM，DataBinding将帮你在视图中实现观察者模式。

如果你使用DataBinding仅仅是为了简化findViewById这样的代码，那么我认为，还不如使用ButterKnife。
如果你做的是完全的MVVM，那DataBinding确实是很好的方式。
