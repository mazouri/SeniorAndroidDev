## 问题1：什么是Dagger？

解题思路：Dagger是一个Java平台的依赖注入(DI)库，它使用注解来实现依赖注入，利用apt工具在编译时生成辅助类，

这些辅助类继承特定的父类或者实现特定的接口。程序在运行时Dagger加载这些辅助类，调用相应的接口完成依赖生成和注入。

由于Dagger没有用反射来注入，对于程序的性能影响非常小，因此更加适用于Android应用的开发。

## 问题2：什么是依赖注入？

解题思路：依赖注入，先来解释一下这个“依赖”的意思。如果在一个类Class A中，存在一个Class B的实例，

那么我们称Class A对Class B有一个依赖。 Show me  your code!

```
public class LoginPresenter {

    LoginApiService loginApi;
    
   public LoginPresenter() {
      loginApi = new LoginApiService();
   }
   
}
```

像这样的代码在Java程序中很普遍，现在如果业务需要变动而需要修改代码，比如：

> 如果现在要改变LoginApiService的生成方式，比如需要用new LoginApiService(String url)来初始化
LoginApiService,那么就需要我们不仅修改LoginApiService的代码，还要修改LoginPresenter的代码。

向上面这样将依赖在构造函数中直接初始化的方式属于硬初始化，类似于硬编码，弊端就在于两个类之间不够独立。

而依赖注入就是实现这样存在依赖的两个类之间的解耦。

show me your code!

```
public class LoginPresenter {

    LoginApiService loginApi;
    
   public LoginPresenter(LoginApiService loginApi) {
      this.loginApi = loginApi;
   }
   
}
```

这种方式就叫做依赖注入，我们将LoginApiService对象作为构造函数的一个参数传入。

在调用LoginPresenter的构造函数方法之前外部就已经初始化好了LoginApiService对象。

像这种不是自己主动初始化依赖，而是通过外部来传入依赖的方式，就是依赖注入。

通过依赖注入，我们可以不用关心依赖的对象是怎么样创建的，这就实现了两个对象间的解耦。

上面这种依赖注入方式叫做传递依赖。

还有一种方式就是像使用Dagger、Butterknife这样的“注入”依赖方式。其中ButterKnife是

实现View的依赖注入，而Dagger实现的是更高层次的模块之间的依赖注入。


## 问题3：为什么使用Dagger？

由于Dagger没有用反射来注入，对于程序的性能影响非常小，因此更加适用于Android应用的开发。

Android在初始化对象的时候经常需要处理各种依赖关系，比如网络访问中使用Retrofit、本地存储使用SharedPreference等，我们都需要在使用它的地方进行实例对象的创建。

另外在搭建模块框架的时候，很注重模块之间的解耦，因此，我们会使用MVP、MVVM、MVVPVM等实现模块解耦，

而Dagger和MVP的结合可以使得M-V-P之间也能实现进一步的解耦。也就是我们对M-V-P三层修改任意一个，对其它

两层影响都很小。

这就是我在使用MVP、MVPVM时选择使用Dagger的原因。

## 问题4：怎么使用Dagger？

解题思路：Dagger2是通过注解来实现依赖注入的，要使用Dagger，需要先理解它的主要注解：

- 1.@Inject 作用 1.标记构造函数，提供依赖  2.在宿主中生成依赖

- 2.@Module 标记一个类，专门用来提供依赖

- 3.@Component 标记一个接口，是依赖和宿主之间的桥梁，将相关依赖注入到宿主中

- 4.@Provides 标记Module中的方法，该方法在需要提供依赖时被调用，在方法里创建依赖实例。

- 5.@Scope 给依赖划定作用域，实现局部单例

- 6.@Singleton 让依赖成为全局单例模式。一般配合@Provides一起出现

- 7.@Qulifier 限定符 自定义Qulifier注解 对于返回相同类型的两个provide方法，提供具体的事例。

> @Singleton是@Scope的一个默认实现； @Name是@Qulifier的一个默认实现，在ApiModule中提供OkHttpClient时用到过。



## 参考博客：

[Android：dagger2让你爱不释手-基础依赖注入框架篇](http://www.jianshu.com/p/cd2c1c9f68d4)

[Android：dagger2让你爱不释手-重点概念讲解、融合篇](http://www.jianshu.com/p/1d42d2e6f4a5)

[Android：dagger2让你爱不释手-终结篇](http://www.jianshu.com/p/65737ac39c44)


[Google官方MVP+Dagger2架构详解【从零开始搭建android框架系列（6）】](http://www.jianshu.com/p/01d3c014b0b1)













