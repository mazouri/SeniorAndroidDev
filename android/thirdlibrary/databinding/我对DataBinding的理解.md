## 1.What？什么是DataBinding？
DataBinding这个库是在15年Google I/O大会推出的，DataBinding的主要作用是让我们可以很方便的实现MVVM架构。DataBinding是一个实现数据与UI绑定的一个框架。由于DataBinding是一个support库，能够最低支持到Android 2.1，因此，它的兼容性也很好。

## 2.Why?为什么要用DataBinding？使用它有什么好处？解决了什么问题？
使用DataBinding可以让我们精简代码，也能提高性能。为什么这个说呢？在不使用DataBinding的时候，我们不可避免的需要编写大量的findViewById()，setText()，setVisibility() ，setEnabled() 或 setOnClickListener() 等这样没有营养的代码，这样的代码数量越多，越容易滋生bug。
    但是如果我们使用DataBinding，我们可以通过 声明式布局 以这种精简的代码 来绑定应用程序的逻辑和布局，这样就不用编写大量的毫无营养的代码了。
    总结DataBinding帮我们解决的几个问题：
    1.让我们不用再使用类似findviewByID这种损害性能的方法
    2.更新UI数据需切换至UI线程问题，（不用使用Looper-Handler），而且将数据分解映射到各个view

### 2.1那使用databinding会带来什么问题呢？
使用 Data Binding 会增加编译出的 apk 文件的类数量和方法数量，如果工程对方法数量很敏感的话，请慎重使用 Data Binding

## 3.怎么使用DataBinding？
因为Android Studio 已经内置了对 Android Data Binding 框架的支持，配置起来也很简单。我们只需要在相应module 的 build.gradle 文件中开启databinding功能即可：
```
android {
    ....
    dataBinding {
        enabled = true
    }
｝
```
使用DataBinding库将会改变我们平时开发时编写布局Layout文件的编写方式，这就是ViewModel的核心，将视图和Model绑定在一起，你只需要修改Model层的值，对应的View层就会监听到自动修改自身。

## 4.DataBinding的布局文件和平时写的布局文件有什么不同？
平时我们写的布局文件，它的根节点都是View元素，比如LinearLayout或者RelativeLayout，然后在跟节点里面写其他view元素。但是DataBinding 的布局文件有些不一样，它是以一个 layout 标签作为根节点，里面是 data 标签与 view 标签。view 标签的内容就是不使用 Data Binding 时的普通布局文件内容，如下：
```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">
   <data>
       <variable name="user" type="com.example.User"/>
   </data>
   <LinearLayout
       android:orientation="vertical"
       android:layout_width="match_parent"
       android:layout_height="match_parent">
       <TextView android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="@{user.firstName}"/>
       <TextView android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="@{user.lastName}"/>
   </LinearLayout>
</layout>
```
在data内描述了一个名为user的变量属性，然后我们就可以在这个layout中使用这个user变量。
我们使用@{}语法写表达式来设置在layout的属性，比如一个 TextView 的 text 设置为 user 的 firstName 属性：
```
<TextView android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="@{user.firstName}"/>
```
请注意这个layout的文件名，DataBinding会根据这个layout的文件名生成一个xxBinding类，这个类继承自ViewDataBiding；如果layout文件名是content_main.xml,则会生成一个ContentMainBinding类，根据官方解释是自动把layout文件名的下滑线去掉，然后采用驼峰式的命名规则，然后再加上Binding后缀。这也说明了android里的layout编译器会把其转换成一个单独的类，每个layout对应一个layout的类。只不过用DataBinding库把这个layout类转换重新实现过。这个类包含了所有的有关于这个布局文件中Views的属性的绑定关系。

## 5.如果绑定数据？
DataBinding的布局文件是MVVM中的V，数据对象是M，可以是POJO，也可以是JavaBean。绑定数据属于ViewModel层。一般的步骤是这样的：
- 1.初始化对应layout的Binding类
- 2.初始化对应layout的Model类
- 3.将对应layout的Model类和Binding类进行绑定

Model类和Binding类的绑定api依赖于data binding layout的配置。这里配置的是：
```
<variable name="user" type="com.example.User"/>
```
即variable name=”user” 这里的name属性是user，对应Binding类的setUser方法，这个方法也是默认自动生成的，采用驼峰式。
然后你就可以通过修改UserModel的值达到自动修改View的效果了

创建bindings最简单的方法是inflating:
```
@Override
protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   //步骤1
   MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
   //也可以这样
   MainActivityBinding binding = MainActivityBinding.inflate(getLayoutInflater());
   //步骤2
   User user = new User("Test", "User");
   //步骤3
   binding.setUser(user);
}
```
如果你在ListView或者RecyclerView的adapter中的item使用data binding, 你可能更愿意这样做：
```
ListItemBinding binding = ListItemBinding.inflate(layoutInflater, viewGroup, false);
//or
ListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, viewGroup, false);
```
## 6.DataBinding的绑定事件处理
问题5中实现控件和数据源的绑定，达到UI和业务逻辑分离，大大简化了代码，而且实现和解耦对于DataBinding的绑定事件处理，我的理解是，绑定‘事件处理方法’到view上，当view的事件发生时，调用这些方法处理事件。
    根据我的总结，绑定事件的步骤是这样的：
- 1.实现事件处理类,比如：
```
public class MyHandlers {

    public final void onClickName(View view) {
        Toast.makeText(view.getContext(), "onClickName()", Toast.LENGTH_SHORT).show();
    }

    public final void onClickAge(View view) {
        Toast.makeText(view.getContext(), "onClickAge()", Toast.LENGTH_SHORT).show();
    }

}
```
- 2.布局文件中的控件选择事件处理方法
```
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android">
    <data>
        <variable name="myHandlers" type="com.example.databinding.event.MyHandlers"/>
        <variable name="user" type="com.example.databinding.data.User" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center_horizontal"
        android:orientation="vertical" >

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="20dp"
            android:text="@{user.name}"
            android:onClick="@{myHandlers.onClickName}"
            android:clickable="true"
            android:textAppearance="?android:attr/textAppearanceMedium" />

        <TextView
            android:id="@+id/textView2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="27dp"
            android:text="@{user.age}"
            android:onClick="@{myHandlers.onClickAge}"
            android:clickable="true"
            android:textAppearance="?android:attr/textAppearanceMedium" />

    </LinearLayout>
</layout>
```
- 3.在代码中实现控件和事件的绑定(这一步很重要，不实现这一步会导致事件无法出发)
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("事件绑定");
    
    User user = new User("myusername", "20");
    MyHandlers myHandlers = new MyHandlers();

    ActivityDataBindingSample2Binding binding = DataBindingUtil.setContentView(this,
        R.layout.activity_data_binding_sample2);
    binding.setUser(user);
    binding.setMyHandlers(myHandlers);
}
```
通过这三个步骤就可以实现事件的绑定。

另外DataBinding的事件绑定有两种方式，一种是Method References（方法引用），一种是Listener Bindings（监听绑定）。来详细介绍下这两种方式，对于方法引用，这种方法类似于在布局文件中定义android:onClick属性，然后在activity中定义相关的方法。相较于 android:onClick ，它的优势在于表达式会在编译时处理，如果函数不存在或者函数签名不对，编译将会报错。在方法引用中，方法的参数必须与监听器对象的参数相匹配。

举个例子：
```
//1.创建事件处理类
public class EventHandler {
    private Context mContext;
    public EventHandler(Context context) {
        mContext = context;
    }

    public void onClickFriend(View view) {
        Toast.makeText(mContext, "onClickFriend", Toast.LENGTH_LONG).show();
    }
}
```
```
//2.布局中控件选择事件方法
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data>
        <variable
            name="handler"
            type="com.connorlin.databinding.handler.EventHandler"/>
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:onClick="@{handler::onClickFriend}"/>
        <!-- 注意：函数名和监听器对象必须对应 -->
    </LinearLayout>
</layout>
```
```
//3.在代码中绑定
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setHandler(new EventHandler());
    }
```
对于第2中方式监听绑定，它是在事件发生时调用，可以使用任意表达式。在方法引用中，方法的参数必须与监听器对象的参数相匹配。在监听绑定中，只要返回值与监听器对象的预期返回值相匹配即可。

举个例子：
```
//1.在事件处理类中定义这样的方法
public void onTaskClick(Task task) {
    task.run();
}
```
```
//2.布局中这样设置
<?xml version="1.0" encoding="utf-8"?>
  <layout xmlns:android="http://schemas.android.com/apk/res/android">
      <data>
          <variable
            name="handler" type="com.connorlin.databinding.handler.EventHandler"/>
        <variable
            name="task" type="com.connorlin.databinding.task.Task"/>
      </data>

      <LinearLayout 
        android:layout_width="match_parent" 
        android:layout_height="match_parent">
          <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:onClick="@{() -> handler.onTaskClick(task)}"/>
      </LinearLayout>
  </layout>
```
```
//3.在代码中绑定
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setHandler(new EventHandler());
    }
```
对于这两种事件绑定的几点区别：
- 1.引用方法要求Gradle插件版本为1.5+；而监听绑定要求的是2.0+
- 2.引用方法要求（事件）响应方法的签名和事件listener中的签名完全一致；而监听绑定只要求
    返回值一致
- 3.引用方法在编译时绑定，而监听绑定在运行时才会执行绑定lambda表达式
- 4.引用方法不可以自定义参数，而监听绑定可以自定义参数
- 5.监听绑定可以编写简单的代码逻辑

## 7.如何实现DataBinding的动态 数据更新？
DataBinding的动态数据更新，也就是将对像数据的变化实时更新到view上。那么如何将对象数据的变化实时更新到view上呢？可以使用DataBinding的setter方法，将对象绑定到view上，但是这种方式有些低效。为什么呢？因为只有变化的数据才需要更新到view上。因此，DataBinding为我们提供了基于观察者模式的一些方法，当我们的数据类实现这些方法后，再修改数据时，就会自动的通知View去更新。

DataBinding提供了三种不同的数据变化的通知机制：
- 1.Observable Objects：可观察对像
- 2.Observable Fields：可观察属性
- 3.Observable Collections：可观察的集合

这三者的实现方式有什么不同呢？

对于可观察对象，当对像中的属性变化时，就会更新到view，可以更新对像所有属性，也可以只更新其中一个。方法是使对像继承BaseObservable，当数据变化时，也就是在setter方法里调用notifyChange或者notifyPropertyChanged函数来通知修改，data bingding就会更新数据到view：
 ```
public class User extends BaseObservable{
    private String userName;

    //使用@Bindable注释，产生BR.XXX
    @Bindable
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);//通知某个变量发生了变化
    }
}
```
如果数据类已经有其他基类了，基于Java是单继承，不能使用BaseObservable类，那么可以implements Observable接口，并使用PropertyChangeRegistry来存取数据。
    
对于可观察属性，当某个属性变化时更新到view。根据数据类型可以使用ObservableFields<T>，也可以使用不需要装箱和拆箱的（可以加快速度）ObservableBoolean, ObservableInt, ObservableLong等。使用方法是这样的：

```
public class User {
    public ObservableField<String> name=new ObservableField<>();
}
//赋值
user.name.set("user "+count++);
//取值
user.name.get();
```
可观察属性的工作原理，其实是对可观察对象的封装，只是每个类只有一个属性值：
```
public class ObservableField<T> extends BaseObservable implements Serializable {
    private T mValue;
    //......
    public T get() {
        return mValue;
    }
    public void set(T value) {
        if (value != mValue) {
            mValue = value;
            notifyChange();
        }
    }
}
```
还有一种可观察的集合方式，当集合中数据变化中，自动更新到view。可以使用ObservableArrayMap和ObservableArrayList。
```
ObservableArrayMap<String, Object> user = new ObservableArrayMap<>();
user.put("firstName", "Google");
user.put("lastName", "Inc.");
user.put("age", 17);
```
在布局中，可以通过键来访问,比如：
```
<data>
    <import type="android.databinding.ObservableMap"/>
    <variable name="user" type="ObservableMap<String, Object>"/>
</data>
…
<TextView
   android:text='@{user["lastName"]}'
   android:layout_width="wrap_content"
   android:layout_height="wrap_content"/>
<TextView
   android:text='@{String.valueOf(1 + (Integer)user["age"])}'
   android:layout_width="wrap_content"
   android:layout_height="wrap_content"/>
```
## 8.DataBinding的基本原理
DataBinding的基本思路是这样的，针对每个Activity的布局，在编译阶段，生成一个ViewDataBinding类的对象，这个对象持有Activity要展示的数据和布局layout中各个view的应用。

这一步解决了性能低的findViewById问题。同时这个对象，还可以：
- 1.将数据分解到各个view；
- 2.在UI线程上更新数据；
- 3.监控数据的变化，实时更新

有了这些功能，要展示的数据已经和展示它的布局紧紧绑定在一起了，这就是该技术叫做DataBinding的原因。

那么DataBinding背后是怎么处理的呢？分为如下几步：
- 1.对布局文件进行预处理，一分为二
    
首先，DataBinding会对根元素为<layout>的布局文件进行预处理(比如activity_main.xml)，处理后，原布局文件会变为没有使用DataBinding时的样子，而且根元素LinearLayout和那些在属性中使用了binding表达式的view都被设置了Tag，而原有的<layout>标签、data标签以及里面的variable标签，还有各个view中的binding表达式都不见了。

DataBinding把最初布局文件中的<data>以及各个view中的binding表达式内容抽取出来，生成了一个名为activtiy_main-layout.xml文件。通过给原有布局文件中的view设置Tag和在生成的文件中(本例中即activtiy_main-layout.xml)使用Tag，使得抽取出来的内容能够与其原先所在的位置对应起来。

- 2.生成ActivityMainBinding与BR类。

现在，DataBinding将会依据上面两个xml文件（即activtiy_main.xml和
activtiy_main-layout.xml）生成两个类，一个类是ActivityMainBinding，它继承自ViewDataBinding，里面包含一下几种内容：
- （1）每个variable标签，对应生成相应的变量
- （2）每一个有id的View，都会有一个以其id为名的public final变量
- （3）每一个没有id但是处理中添加了Tag 的View，都对应有一个private final的变量生成BR类也很简单，DataBinding会根据<data/>标签中的每一个variable生成对应的BR常量，并且对象类中添加@Bindable注解的方法对应的属性也会生成对应的BR常量。实际上，BR中的常量是一种标识符，它对应一个会发生变化的数据，当数据改变后，你可以用该标识符通知DataBinding，很快，DataBinding就会用新的数据去更新UI。

- 3.生成ActivityMainBinding实例并绑定

这个步骤又可以分成3个小过程：

首先是Inflate 处理后的布局文件，得到root ViewGroup变量；

然后根据这个root变量，构造ActivityMainBinding实例对象；在这里，ActivityMainBinding会首先遍历root，根据各个View的Tag或者id，初始化自己的fields，最后会invalidateAll引发数据绑定。

在数据绑定这一步，ActivityMainBinding将会计算各个view上的binding表达式，然后赋值给view相应的属性。

至此，DataBinding背后所做的事儿我们就清楚了。

## 9.DataBinding的几个注解方法
（1）@BindingMethod注解
 
 这个注解是用来关联控件中提供的方法的。当控件的某些属性的setXXX方法并没有对应的自定义属性，就需要 @BindingMethod 来“牵绳拉线”，创建一个新的自定义属性:
```
@BindingMethods({
        @BindingMethod(type = android.widget.ImageView.class, attribute = "android:tint", method = "setImageTintList"),
        @BindingMethod(type = android.widget.ImageView.class, attribute = "android:tintMode", method = "setImageTintMode"),
})
```
它将ImageView里面的两个方法关联了两个新的xml属性，这样子可以更加方便我们在xml中使用这两个方法。

（2）@BindingAdapter注解

当控件里面没有提供某个属性的setXXX方法，又或者这个setXXX方法名字我们不喜欢，还有就是明明就是设置控件的某个属性的，但方法名却不是以set开头的，这些情况我们可以使用BindingAdapter这个强大的注解。简单的说就是一个用@BindingAdapter修饰的静态方法可以自定义属性的setter操作。

这里我分享下我平时利用BindingAdapter来使用RecyclerView时的写法，如下所示：
```
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>
        <variable
            name="adapter"
            type="android.support.v7.widget.RecyclerView.Adapter" />
        <variable
            name="layoutManager"
            type="android.support.v7.widget.RecyclerView.LayoutManager" />
    </data>

    <android.support.v7.widget.RecyclerView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="16dp"
        app:adapter="@{adapter}"
        app:layoutManager="@{layoutManager}" />
</layout>
```
直接在xml里面给RecyclerView绑定adapter和layoutManager，要做到这一点当然少不了强大的BindingAdapter:
```
public class UtilsBindingAdapter {
   @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager manager) {
        view.setLayoutManager(manager);
   }

   @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }
}
```
如果还需要给RecyclerView设置什么的话，完全可以按照上面的去写。在Java代码中的调用就是：
```
   mBinding.setLayoutManager(new LinearLayoutManager(this));
   mBinding.setAdapter(new RecyclerViewAdapter(this));
```
## 10.DataBinding应该怎么绑定RecyclerView？
首先，我们需要定义一个基类的ViewHolder，方便我们使用DataBinding：
```
public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T mBinding;

    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}
```
接着RecyclerView的Adapter直接使用这个BindingViewHolder，在onCreateViewHolder里面生成该ViewHolder:
```
public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(mInflater, R.layout.item_xxx,parent, false);
        return new BindingViewHolder(binding);
}
```
然后在onBindViewHolder里面进行数据绑定和设置Listener:
```
  @Override
  public void onBindViewHolder(BindingViewHolder holder, int position) {
        final String item = mDatas.get(position);
        //给ViewHolder的xml里面的item变量进行数据绑定
        holder.getBinding().setVariable(BR.item, item);
        //建立绑定关系,在主线程中实时更新任何进行了绑定的数据
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(item);
            }
          }
       });
   }
```
## 简化RecyclerView的DataBinding的方案可以选用第三方的：

[DataBindingAdapter](https://github.com/markzhai/DataBindingAdapter)

或者

[RxRecyclerAdapter](https://github.com/ahmedrizwan/RxRecyclerAdapter)


## 几点我不使用DataBinding的原因？
[4 Reasons I’m Not Using Android Data Binding](https://medium.com/@Miqubel/4-reasons-im-not-using-android-data-binding-e62127c2650c#.ijq289s5j)
