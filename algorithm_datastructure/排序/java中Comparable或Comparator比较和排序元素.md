# java中Comparable或Comparator比较和排序元素

## 什么是Comparable？什么是Comparator？

### Comparable是排序接口。它是在集合内部定义方法实现的排序。

若一个类实现了Comparable接口，就意味着“该类支持排序”。  即然实现Comparable接口的类支持排序，假设现在存在“实现Comparable接口的类的对象的List列表(或数组)”，则该List列表(或数组)可以通过 Collections.sort（或 Arrays.sort）进行排序。

此外，“实现Comparable接口的类的对象”可以用作“有序映射(如TreeMap)”中的键或“有序集合(TreeSet)”中的元素，而不需要指定比较器。

Comparable 接口仅仅只包括一个函数，它的定义如下：
```
public interface Comparable<T> {
    public int compareTo(T o);
}
```
> 假设我们通过 x.compareTo(y) 来“比较x和y的大小”。若返回“负数”，意味着“x比y小”；返回“零”，意味着“x等于y”；返回“正数”，意味着“x大于y”。

### Comparator 是比较器接口。是在集合外部实现的排序，如果想实现排序，就需要在集合外定义Comparator接口的方法或在集合内实现Comparable接口的方法。

我们若需要控制某个类的次序，而该类本身不支持排序(即没有实现Comparable接口)；那么，我们可以建立一个“该类的比较器”来进行排序。这个“比较器”只需要实现Comparator接口即可。

也就是说，我们可以通过“实现Comparator类来新建一个比较器”，然后通过该比较器对类进行排序。

Comparator 接口仅仅只包括两个个函数，它的定义如下：
```
public interface Comparator<T> {

    int compare(T o1, T o2);

    boolean equals(Object obj);
}
```
> (01) 若一个类要实现Comparator接口：它一定要实现compareTo(T o1, T o2) 函数，但可以不实现 equals(Object obj) 函数。

> 为什么可以不实现 equals(Object obj) 函数呢？ 因为任何类，默认都是已经实现了equals(Object obj)的。 Java中的一切类都是继承于java.lang.Object，在Object.java中实现了equals(Object obj)函数；所以，其它所有的类也相当于都实现了该函数。

> (02) int compare(T o1, T o2) 是“比较o1和o2的大小”。返回“负数”，意味着“o1比o2小”；返回“零”，意味着“o1等于o2”；返回“正数”，意味着“o1大于o2”。

## Comparator 和 Comparable 的比较

Comparable相当于“内部比较器”，而Comparator相当于“外部比较器”。

## 实例代码见

[Java 中 Comparable 和 Comparator 比较](http://www.cnblogs.com/skywang12345/p/3324788.html)

[Android学习之使用Comparable或Comparator比较和排序元素](http://blog.csdn.net/qq_23940659/article/details/51013626)

## Comparator原理

[List的sort中的Comparator的使用和原理](http://blog.csdn.net/rually/article/details/44673785)

List中sort方法 --> Arrays.sort --> TimSort.sort --> 二分法排序binarySort, 需要比较器comparator进行比较判断，来决定升序还是降序，所以在使用list的sort方法时，需要实现comparator接口。


