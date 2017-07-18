package javaart.classloader;

/**
 * 加载一个类的步骤：
 * 1.先判断是否已经加载过了，没有再进行加载
 * 2.有父类时，调用父类的loadClass加载
 * 3.没有父类时调用BootStrapClassLoader加载
 * 4.如果仍然没找到，那么调用自己的findClass进行加载
 *
 * Created by wangdongdong on 17/7/17.
 */
public class ClassLoaderImpl {

    /*
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                //1.先判断是否已经加载过了
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
                    try {
                        if (parent != null) {
                            2.有父类时，调用父类的loadClass加载
                            c = parent.loadClass(name, false);
                        } else {
                            3.没有父类时调用BootStrapClassLoader加载
                            c = findBootstrapClassOrNull(name);
                        }
                    } catch (ClassNotFoundException e) {
                        // ClassNotFoundException thrown if class not found
                        // from the non-null parent class loader
                    }

                    if (c == null) {
                        //4.如果仍然没找到，那么调用自己的findClass进行加载
                        long t1 = System.nanoTime();
                        c = findClass(name);

                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
     */
}
