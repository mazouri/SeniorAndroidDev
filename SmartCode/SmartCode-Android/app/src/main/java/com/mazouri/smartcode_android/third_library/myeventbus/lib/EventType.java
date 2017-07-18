package com.mazouri.smartcode_android.third_library.myeventbus.lib;

/**
 * 用于确定函数的唯一性，参数类型、type两个条件保证了对象的唯一性。
 *
 * 通过该类的对象来查找注册了相应类型和tag的所有订阅者{@see Subscription}, 并且在接到消息时调用所有订阅者对应的函数.
 *
 * Created by wangdongdong on 17/7/14.
 */

public class EventType {

    /**
     * 默认的类型
     */
    public static final String DEFAULT_TYPE = "default_type";

    /**
     * 函数的type
     */
    public String type = DEFAULT_TYPE;

    /**
     * 参数类型
     */
    Class<?> paramClass;

    public EventType(Class<?> aClass) {
        this(aClass, DEFAULT_TYPE);
    }

    public EventType(Class<?> aClass, String aType) {
        paramClass = aClass;
        type = aType;
    }

    @Override
    public String toString() {
        return "EventType [paramClass=" + paramClass.getName() + ", type=" + type + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (paramClass == null ? 0 : paramClass.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }
}
