package cn.yeshaoting.sd.leetcode.value;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
    description:
    author: yeshaoting
    time: 2019-08-13 19:55
*/
public class Topic1114 {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 1; i++) {
//        Solution1114_A handler = new Solution1114_A();
//            Solution1114_B handler = new Solution1114_B();
//            Solution1114_C handler = new Solution1114_C();
//        Solution1114_D handler = new Solution1114_D();
            Solution1114_E handler = new Solution1114_E();

            new Thread(() -> {
                try {
                    handler.third(() -> System.out.println("third"));
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

            }, "third").start();
            Thread.sleep(10);

            new Thread(() -> {
                try {
                    handler.second(() -> System.out.print("second"));
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

            }, "second").start();
            Thread.sleep(10);

            new Thread(() -> {
                try {
                    handler.first(() -> System.out.print("first"));
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

            }, "first").start();
        }
    }

}


class Solution1114_A {

    private volatile int lock = 0;

    public Solution1114_A() {

    }

    public void second(Runnable printSecond) throws InterruptedException {
        casGet(1);

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();

        lock++;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        casGet(0);

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        lock++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        casGet(2);

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();

        lock++;
    }

    private void casGet(int except) {
        for (; ; ) {
            if (lock == except)
                return;
        }
    }

}

class Solution1114_B {

    private Semaphore secondLock = new Semaphore(0);
    private Semaphore thirdLock = new Semaphore(0);

    public Solution1114_B() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        secondLock.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        secondLock.acquire();

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();

        thirdLock.release(1);
    }

    public void third(Runnable printThird) throws InterruptedException {
        thirdLock.acquire(1);

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

}

class Solution1114_C {

    private Object lock = new Object();
    private volatile int state = 0;

    public Solution1114_C() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();

            state++;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (state != 1) {
                lock.wait();
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();

            state++;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (state != 2) {
                lock.wait();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();

            state++;
            lock.notifyAll();
        }
    }

}

class Solution1114_D {

    private int state = 0;

    private ReentrantLock lock = new ReentrantLock();
    private Condition secondC = lock.newCondition();
    private Condition thirdC = lock.newCondition();

    public Solution1114_D() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        state = 1;
        secondC.signal();
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        if (state != 1) {
            secondC.await();
        }

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();

        state = 2;
        thirdC.signal();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        if (state != 2) {
            thirdC.await();
        }

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();

        lock.unlock();
    }

}

// 有问题版本
class Solution1114_E {

    private Semaphore lock = new Semaphore(0);

    public Solution1114_E() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        lock.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.acquire();

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();

        lock.release(2);
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.acquire(2);

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

}