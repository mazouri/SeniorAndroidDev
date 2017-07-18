package javaart.keyword_instanceof;

/**
 * Created by wangdongdong on 17/7/17.
 */
public class InstanceofImpl {

    public static <T> boolean isInstance(Object obj, Class<T> tClass) {
        if (obj == null) {
            return false;
        }

        try {
            T targetClass = (T) obj;
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        System.out.println(isInstance(apple, Fruit.class));
        System.out.println(isInstance(apple, Apple.class));
        System.out.println(isInstance(apple, Orange.class));
        System.out.println(isInstance(apple, Meat.class));
        System.out.println(null instanceof Object);
    }
}
