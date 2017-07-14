package javaart.create_class_instance_by_5_methods;

/**
 * 使用new关键字创建对象 -> 调用了构造函数
 *
 * 这是最常见也是最简单的创建对象的方式了。通过这种方式，我们可以调用任意的构造函数(无参的和带参数的)。
 *
 * Created by wangdongdong on 17/7/13.
 */
public class CreateByNew {

    public static void main(String[] args) {
        Book book = new Book();
        /*
               0: new           #2                  // class javaart/create_class_instance_by_5_methods/Book
               3: dup
               4: invokespecial #3                  // Method javaart/create_class_instance_by_5_methods/Book."<init>":()V
               7: astore_1
         */


        Book techBook = new Book("Learning Java", 200, 1996, "Tech");
        /*
               8: new           #2                  // class javaart/create_class_instance_by_5_methods/Book
              11: dup
              12: ldc           #4                  // String Learning Java
              14: sipush        200
              17: sipush        1996
              20: ldc           #5                  // String Tech
              22: invokespecial #6                  // Method javaart/create_class_instance_by_5_methods/Book."<init>":(Ljava/lang/String;IILjava/lang/String;)V
              25: astore_2
              26: return
         */
    }
}
