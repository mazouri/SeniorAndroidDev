# HashMap相关问题

[**必须要看**：HashMap实现原理及源码分析](http://www.cnblogs.com/chengxiao/p/6059914.html)

http://www.cnblogs.com/zywu/p/5753736.html

http://www.admin10000.com/document/3322.html

<img src="http://www.admin10000.com/UploadFiles/Document/201311/16/20131116091617994485.JPG">

## 你知道HashMap的工作原理吗？

HashMap是基于hashing的原理，我们使用put(key, value)存储对象到HashMap中，使用get(key)从HashMap中获取对象。当我们给put()方法传递键和值时，我们先对键调用hashCode()方法，返回的hashCode用于找到bucket位置来储存Entry对象。”这里关键点在于指出，HashMap是在bucket中储存键对象和值对象，作为Map.Entry。

## 当两个对象的hashcode相同会发生什么？

因为hashcode相同，所以它们的bucket位置相同，‘碰撞’会发生。因为HashMap使用LinkedList存储对象，这个Entry(包含有键值对的Map.Entry对象)会存储在LinkedList中。(当向 HashMap 中添加 key-value 对，由其 key 的 hashCode() 返回值决定该 key-value 对（就是 Entry 对象）的存储位置。当两个 Entry 对象的 key 的 hashCode() 返回值相同时，将由 key 通过 eqauls() 比较值决定是采用覆盖行为（返回 true），还是产生 Entry 链（返回 false）。)

## 如果两个键的hashcode相同，你如何获取值对象？

当我们调用get()方法，HashMap会使用键对象的hashcode找到bucket位置，然后获取值对象。如果有两个值对象储存在同一个bucket，将会遍历LinkedList直到找到值对象。找到bucket位置之后，会调用keys.equals()方法去找到LinkedList中正确的节点，最终找到要找的值对象。(当程序通过 key 取出对应 value 时，系统只要先计算出该 key 的 hashCode() 返回值，在根据该 hashCode 返回值找出该 key 在 table 数组中的索引，然后取出该索引处的 Entry，最后返回该 key 对应的 value 即可。)

## 如果HashMap的大小超过了负载因子(load factor)定义的容量，怎么办？

当一个map填满了75%的bucket时候，和其它集合类(如ArrayList等)一样，将会创建原来HashMap大小的两倍的bucket数组，来重新调整map的大小，并将原来的对象放入新的bucket数组中。这个过程叫作rehashing，因为它调用hash方法找到新的bucket位置。

## 你了解重新调整HashMap大小存在什么问题吗？

当重新调整HashMap大小的时候，确实存在条件竞争，因为如果两个线程都发现HashMap需要重新调整大小了，它们会同时试着调整大小。在调整大小的过程中，存储在LinkedList中的元素的次序会反过来，因为移动到新的bucket位置的时候，HashMap并不会将元素放在LinkedList的尾部，而是放在头部，这是为了避免尾部遍历(tail traversing)。如果条件竞争发生了，那么就死循环了。

## 为什么String, Interger这样的wrapper类适合作为键？

String, Interger这样的wrapper类作为HashMap的键是再适合不过了，而且String最为常用。因为String是不可变的，也是final的，而且已经重写了equals()和hashCode()方法了。其他的wrapper类也有这个特点。不可变性是必要的，因为为了要计算hashCode()，就要防止键值改变，如果键值在放入时和获取时返回不同的hashcode的话，那么就不能从HashMap中找到你想要的对象。不可变性还有其他的优点如线程安全。如果你可以仅仅通过将某个field声明成final就能保证hashCode是不变的，那么请这么做吧。因为获取对象的时候要用到equals()和hashCode()方法，那么键对象正确的重写这两个方法是非常重要的。如果两个不相等的对象返回不同的hashcode的话，那么碰撞的几率就会小些，这样就能提高HashMap的性能。

## 我们可以使用自定义的对象作为键吗？

当然你可能使用任何对象作为键，只要它遵守了equals()和hashCode()方法的定义规则，并且当对象插入到Map中之后将不会再改变了。如果这个自定义对象时不可变的，那么它已经满足了作为键的条件，因为当它创建之后就已经不能改变了。

## 我们可以使用CocurrentHashMap来代替HashTable吗？

我们知道HashTable是synchronized的，但是ConcurrentHashMap同步性能更好，因为它仅仅根据同步级别对map的一部分进行上锁。ConcurrentHashMap当然可以代替HashTable，但是HashTable提供更强的线程安全性。看看这篇博客查看Hashtable和ConcurrentHashMap的区别。

## HashMap与Hashtable的区别？

HashMap可以接受null键值和值，而Hashtable则不能。

Hashtable是线程安全的，通过synchronized实现线程同步。而HashMap是非线程安全的，但是速度比Hashtable快。

## HashMap的2种遍历方式？
> 效率高,以后一定要使用此种方式！
```
    Map map = new HashMap();
　　Iterator iter = map.entrySet().iterator();
　　while (iter.hasNext()) {
　　  Map.Entry entry = (Map.Entry) iter.next();
　　  Object key = entry.getKey();
　　  Object val = entry.getValue();
　　}
```
> 效率低,以后尽量少使用！
```
    Map map = new HashMap();
　　Iterator iter = map.keySet().iterator();
　　while (iter.hasNext()) {
　　  Object key = iter.next();
　　  Object val = map.get(key);
　　}
```
可是为什么第一种比第二种方法效率更高呢？

HashMap这两种遍历方法是分别对keyset及entryset来进行遍历，但是对于keySet其实是遍历了2次，一次是转为iterator，一次就从hashmap中取出key所对于的value。而entryset只是遍历了第一次，它把key和value都放到了entry中，即键值对，所以就快了。
