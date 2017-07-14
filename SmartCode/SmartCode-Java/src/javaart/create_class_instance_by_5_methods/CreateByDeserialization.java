package javaart.create_class_instance_by_5_methods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by wangdongdong on 17/7/13.
 */
public class CreateByDeserialization {
    public static void main(String[] args) {
        try {
            Book techBook = new Book("Learning Java", 200, 1996, "Tech");
            System.out.println(techBook + ", hashcode : " + techBook.hashCode());

            // Serialization
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"));
            out.writeObject(techBook);
            out.close();
            //Deserialization
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"));
            Book bookCopy = (Book) in.readObject();
            in.close();
            System.out.println(bookCopy + ", hashcode : " + bookCopy.hashCode());

            bookCopy.setName("Learning Java copy");
            System.out.println(bookCopy + ", hashcode : " + bookCopy.hashCode());

            /*
            javaart.create_class_instance_by_5_methods.Book@c8b20e03, hashcode : -927855101
            javaart.create_class_instance_by_5_methods.Book@c8b20e03, hashcode : -927855101
            javaart.create_class_instance_by_5_methods.Book@8c90a050, hashcode : -1936678832
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
