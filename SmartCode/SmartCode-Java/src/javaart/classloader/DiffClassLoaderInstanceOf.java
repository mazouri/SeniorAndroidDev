package javaart.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 不同的类加载器对instanceOf关键字运算的影响
 *
 * 自定义类加载器
 *
 * Created by wangdongdong on 17/7/17.
 */
public class DiffClassLoaderInstanceOf {

    public static void main(String[] args) {

        //自定义类加载器，需要重写它的loadClass方法；将class文件转化为字节流
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf('.') + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        try {
            Object obj = myClassLoader.loadClass("javaart.classloader.DiffClassLoaderInstanceOf").newInstance();

            System.out.println(obj.getClass()); //class javaart.classloader.DiffClassLoaderInstanceOf
            System.out.println(javaart.classloader.DiffClassLoaderInstanceOf.class);//class javaart.classloader.DiffClassLoaderInstanceOf
            System.out.println(obj instanceof javaart.classloader.DiffClassLoaderInstanceOf); // false

            /*
            为什么是false？因为此时虚拟机存在了2个DiffClassLoaderInstanceOf类，
            一个由系统的类加载器加载，另一个由我们自定义的类加载器加载的。
            虽然来自同一个Class文件，但是它们是2个独立的类。
             */

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
