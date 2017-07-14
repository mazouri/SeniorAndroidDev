package javaart.enum_useful;

/**
 * 通过enum关键字来实现枚举，在枚举中需要注意的有：
 * 1.枚举中的属性必须放在最前面，一般使用大写字母表示
 * 2. 枚举中可以和java类一样定义方法
 * 3. 枚举中的构造方法必须是私有的
 *
 * 可以在枚举属性后面添加()来调用指定参数的构造方法，添加{}来实现其对应的匿名内部类
 *
 * Created by wangdongdong on 17/7/14.
 */
public enum WeekDay {  //public abstract class javaart.enum_useful.WeekDay extends java.lang.Enum<javaart.enum_useful.WeekDay>
    SUN(0){  //public static final javaart.enum_useful.WeekDay SUN;
        @Override
        public WeekDay nextDay() {
            return MON;
        }
    },

    MON(1) {
        @Override
        public WeekDay nextDay() {
            return SUN;
        }
    };

    public abstract WeekDay nextDay();

    private int value;

    WeekDay(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println(WeekDay.MON.value);
    }
}
