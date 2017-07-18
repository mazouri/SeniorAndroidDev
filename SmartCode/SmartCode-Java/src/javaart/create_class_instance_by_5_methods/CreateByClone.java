package javaart.create_class_instance_by_5_methods;

/**
 * 使用clone方法 -> 没有调用构造函数
 *
 * Created by wangdongdong on 17/7/13.
 */
public class CreateByClone {
    public static void main(String[] args) {
        Book techBook = new Book("Learning Java", 200, 1996, "Tech");
        /*
           0: new           #2                  // class javaart/create_class_instance_by_5_methods/Book
           3: dup
           4: ldc           #3                  // String Learning Java
           6: sipush        200
           9: sipush        1996
          12: ldc           #4                  // String Tech
          14: invokespecial #5                  // Method javaart/create_class_instance_by_5_methods/Book."<init>":(Ljava/lang/String;IILjava/lang/String;)V
          17: astore_1

         */
        Book cloneBook = (Book) techBook.clone();
        System.out.println(cloneBook.getName()); //Learning Java
        /*
          18: aload_1
          19: invokevirtual #6                  // Method javaart/create_class_instance_by_5_methods/Book.clone:()Ljava/lang/Object;
          22: checkcast     #2                  // class javaart/create_class_instance_by_5_methods/Book
          25: astore_2
          26: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
          29: aload_2
          30: invokevirtual #8                  // Method javaart/create_class_instance_by_5_methods/Book.getSignalName:()Ljava/lang/String;
          33: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
          36: return
         */
    }
}
