package javaart.create_class_instance_by_5_methods;

import java.io.Serializable;

/**
 * Created by wangdongdong on 17/7/13.
 */
public class Book implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    String name; //书名  //java.lang.String name;
    int pages; //页数    //int pages;
    int date; //发布时间  //int date;
    String type; //类型  java.lang.String type;

    public Book() {
    }
    /*
      public javaart.create_class_instance_by_5_methods.Book();
        Code:
           0: aload_0
           1: invokespecial #1                  // Method java/lang/Object."<init>":()V
           4: return
     */

    public Book(String name, int pages, int date, String type) {
        this.name = name;
        this.pages = pages;
        this.date = date;
        this.type = type;
    }
    /*
      public javaart.create_class_instance_by_5_methods.Book(java.lang.String, int, int, java.lang.String);
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: aload_1
       6: putfield      #2                  // Field name:Ljava/lang/String;
       9: aload_0
      10: iload_2
      11: putfield      #3                  // Field pages:I
      14: aload_0
      15: iload_3
      16: putfield      #4                  // Field date:I
      19: aload_0
      20: aload         4
      22: putfield      #5                  // Field type:Ljava/lang/String;
      25: return
     */

    public String getName() {
        return name;
    }
    /*
      public java.lang.String getSignalName();
        Code:
           0: aload_0
           1: getfield      #2                  // Field name:Ljava/lang/String;
           4: areturn
     */

    public void setName(String name) {
        this.name = name;
    }
    /*
      public void setSignalName(java.lang.String);
        Code:
           0: aload_0
           1: aload_1
           2: putfield      #2                  // Field name:Ljava/lang/String;
           5: return
     */

    public int getPages() {
        return pages;
    }
    /*
      public int getPages();
        Code:
           0: aload_0
           1: getfield      #3                  // Field pages:I
           4: ireturn

     */

    public void setPages(int pages) {
        this.pages = pages;
    }
    /*
      public void setPages(int);
        Code:
           0: aload_0
           1: iload_1
           2: putfield      #3                  // Field pages:I
           5: return

     */

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
}
