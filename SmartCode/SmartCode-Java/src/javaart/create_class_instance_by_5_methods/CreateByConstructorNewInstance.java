package javaart.create_class_instance_by_5_methods;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 使用Constructor类的newInstance方法
 *
 * 调用无参或者有参数的和私有的构造函数
 *
 * 和Class类的newInstance方法很像， java.lang.reflect.Constructor类里也有一个newInstance方法可以创建对象
 *
 * Created by wangdongdong on 17/7/13.
 */
public class CreateByConstructorNewInstance {
    public static void main(String[] args) {
        try {
            Constructor<Book> constructor = Book.class.getConstructor(); //获取无参构造函数
            Book book = constructor.newInstance();
            /*
               0: ldc           #2                  // class javaart/create_class_instance_by_5_methods/Book
               2: iconst_0
               3: anewarray     #3                  // class java/lang/Class
               6: invokevirtual #4                  // Method java/lang/Class.getConstructor:([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
               9: astore_1
              10: aload_1
              11: iconst_0
              12: anewarray     #5                  // class java/lang/Object
              15: invokevirtual #6                  // Method java/lang/reflect/Constructor.newInstance:([Ljava/lang/Object;)Ljava/lang/Object;
              18: checkcast     #2                  // class javaart/create_class_instance_by_5_methods/Book
              21: astore_2
             */

            Class[] parametersList = {String.class, Integer.TYPE, Integer.TYPE, String.class};   // 构造method的参数(class)类型列表
            Constructor<Book> techBookConstructor = Book.class.getConstructor(parametersList);
            Book techBook = techBookConstructor.newInstance("Learning Java", 100, 1996, "Tech");
            System.out.println(techBook.getName());
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
