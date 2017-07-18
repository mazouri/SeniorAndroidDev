package javaart.keyword_null;

/**
 * Created by wangdongdong on 17/7/17.
 */
public class NullClass {

    public static boolean canFinish() {
        System.out.println("here");
        return true;
    }

    public static void main(String[] args) {
        boolean canFinish = ((NullClass) null).canFinish(); //true 能够打印here 因为null是可以调用 static 方法的
    }
}
