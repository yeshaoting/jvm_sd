package cn.yeshaoting.sd.leetcode.value;

import java.io.IOException;
import java.util.concurrent.Semaphore;

/*
    description:
    author: yeshaoting
    time: 2019-08-13 20:23
*/
public class Topic1115 {

    public static void main(String[] args) throws IOException, InterruptedException {
//        Solution1115_A handler = new Solution1115_A(7);
//        Solution1115_B handler = new Solution1115_B(7);
        Solution1115_C handler = new Solution1115_C(7);

        new Thread(() -> {
            try {
                handler.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }, "a").start();

        new Thread(() -> {
            try {
                handler.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }, "b").start();

    }
    
}


class Solution1115_A {

    private int n;
    private volatile int lock = 0;

    public Solution1115_A(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            casGet(0);

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();

            lock++;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            casGet(1);

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();

            lock++;
        }
    }

    private void casGet(int except) {
        for (;;) {
            if (lock % 2 == except)
                return;
        }
    }
}

class Solution1115_B {

    private int n;
    private volatile boolean state = false;
    private Object lock = new Object();

    public Solution1115_B(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        synchronized (lock) {
            for (int i = 0; i < n; i++) {
                if (state == true) {
                    lock.wait();
                }

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();

                state = true;
                lock.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        synchronized (lock) {
            for (int i = 0; i < n; i++) {
                if (state == false) {
                    lock.wait();
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();

                state = false;
                lock.notifyAll();
            }
        }
    }

}

class Solution1115_C {

    private int n;
    private Semaphore fooLock = new Semaphore(1);
    private Semaphore barLock = new Semaphore(0);

    public Solution1115_C(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            fooLock.acquire();

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();

            barLock.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            barLock.acquire();

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();

            fooLock.release();
        }
    }

}