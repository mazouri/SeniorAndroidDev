package javaart.create_class_instance_by_5_methods;

/**
 * 使用Class类的newInstance方法 -> 调用无参的构造函数创建对象
 *
 * 这个newInstance方法调用无参的构造函数创建对象
 *
 * Created by wangdongdong on 17/7/13.
 */
public class CreateByClassNewInstance {
    public static void main(String[] args) {
        try {
            Book book = (Book) Class.forName("javaart.create_class_instance_by_5_methods.Book").newInstance();
            /*
               0: ldc           #2                  // String javaart.create_class_instance_by_5_methods.Book
               2: invokestatic  #3                  // Method java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
               5: invokevirtual #4                  // Method java/lang/Class.newInstance:()Ljava/lang/Object;
               8: checkcast     #5                  // class javaart/create_class_instance_by_5_methods/Book
              11: astore_1
             */
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
