package com.mazouri.smartcode_android.third_library.myeventbus.lib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于注解 事件接收的方法
 *
 * 包涵事件的标识符（事件类型）、事件执行的线程
 *
 * Created by wangdongdong on 17/7/14.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscriber {

    String type() default EventType.DEFAULT_TYPE;
}
