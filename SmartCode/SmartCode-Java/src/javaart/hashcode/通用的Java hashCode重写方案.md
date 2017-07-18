# 通用的Java hashCode重写方案

Google首席Java架构师Joshua Bloch在他的著作《Effective Java》中提出了一种简单通用的hashCode算法
1. 初始化一个整形变量，为此变量赋予一个非零的常数值，比如int result = 17;
2. 选取equals方法中用于比较的所有域，然后针对每个域的属性进行计算：
  (1) 如果是boolean值，则计算f ? 1:0
  (2) 如果是byte\char\short\int,则计算(int)f
  (3) 如果是long值，则计算(int)(f ^ (f >>> 32))
  (4) 如果是float值，则计算Float.floatToIntBits(f)
  (5) 如果是double值，则计算Double.doubleToLongBits(f)，然后返回的结果是long,再用规则(3)去处理long,得到int
  (6) 如果是对象应用，如果equals方法中采取递归调用的比较方式，那么hashCode中同样采取递归调用hashCode的方式。　　否则需要为这个域计算一个范式，比如当这个域的值为null的时候，那么hashCode 值为0
  (7) 如果是数组，那么需要为每个元素当做单独的域来处理。如果你使用的是1.5及以上版本的JDK，那么没必要自己去　　　　重新遍历一遍数组，java.util.Arrays.hashCode方法包含了8种基本类型数组和引用数组的hashCode计算，算法同上，
　　java.util.Arrays.hashCode(long[])的具体实现:

```
public static int hashCode(long a[]) {  
        if (a == null)  
            return 0;  
  
        int result = 1;  
        for (long element : a) {  
            int elementHash = (int)(element ^ (element >>> 32));  
            result = 31 * result + elementHash;  
        }  
  
        return result;  
}
```

Arrays.hashCode(...)只会计算一维数组元素的hashCOde,如果是多维数组，那么需要递归进行hashCode的计算，那么就需要使用Arrays.deepHashCode(Object[])方法。

3. 最后，要如同上面的代码，把每个域的散列码合并到result当中：result = 31 * result + elementHash;
4. 测试，hashCode方法是否符合文章开头说的基本原则，这些基本原则虽然不能保证性能，但是可以保证不出错。



2. 为什么每次需要使用乘法去操作result?　主要是为了使散列值依赖于域的顺序，还是上面的那个例子，Test t = new Test(1, 0)跟Test t2 = new Test(0, 1), t和t2的最终hashCode返回值是不一样的。

3. 为什么是31? 31是个神奇的数字，因为任何数n * 31就可以被JVM优化为 (n << 5) -n,移位和减法的操作效率要比乘法的操作效率高的多。
