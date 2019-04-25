package cn.yeshaoting.sd;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SingletonTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService worker = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 2000; i++) {
            worker.execute(new Runnable() {
                @Override
                public void run() {
//                    Singleton.getInstance1();
//                    Singleton.getInstance2();
//                    Singleton.getInstance3();
//                    Singleton.getInstance4();
                    Singleton.getInstance5();
                }
            });
        }

        worker.awaitTermination(5, TimeUnit.SECONDS);
        worker.shutdown();
    }
}

class Singleton {

    private static Singleton unsafe;
    private static Singleton safe;
    private static final Singleton hugury = new Singleton();
    private static volatile Singleton dcl;
    private static final AtomicReference<Boolean> flag = new AtomicReference<>(false);
    private static final AtomicReference<Singleton> cas = new AtomicReference<>(null);

    private Singleton() {
        System.out.println("init singleton class");
    }

    // 不安全的懒汉
    public static Singleton getInstance1() {
        if (unsafe != null) {
            return unsafe;
        }

        unsafe = new Singleton();
        return unsafe;
    }

    // 安全的懒汉
    public static synchronized Singleton getInstance2() {
        if (safe != null) {
            return safe;
        }

        safe = new Singleton();
        return safe;
    }

    // 安全的饿汉
    public static Singleton getInstance3() {
        return hugury;
    }

    // DCL
    public static Singleton getInstance4() {
        if (dcl != null) {
            return dcl;
        }

        synchronized (Singleton.class) {
            if (dcl != null) {
                return dcl;
            }

            dcl = new Singleton();
        }

        return dcl;
    }

    // 外界拿到的都是同一个实例，但是不能保证类不被多次初始化
    public static Singleton getInstance5() {
        for (; ; ) {
            Singleton singleton = cas.get();
            if (null != singleton) {
                return singleton;
            }

            singleton = new Singleton();
            if (cas.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }

}
