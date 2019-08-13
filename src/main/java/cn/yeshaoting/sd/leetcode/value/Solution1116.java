package cn.yeshaoting.sd.leetcode.value;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/*
    description:
    author: yeshaoting
    time: 2019-08-11 19:46
*/
class ZeroEvenOdd {
    private int n;

    // 当前要输出的非0整数
    private volatile int current = 1;
    private volatile int lock = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            casGet(0);
            if (current > n) {
                return;
            }

            printNumber.accept(0);
            lock = current % 2 == 1 ? 1 : 2;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n / 2 + 1; i++) {
            casGet(1);
            if (current > n) {
                return;
            }

            printNumber.accept(current);
            current++;
            lock = 0;
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n / 2; i++) {
            casGet(2);
            if (current > n) {
                return;
            }

            printNumber.accept(current);
            current++;
            lock = 0;
        }
    }

    private void casGet(int except) {
        for (; ; ) {
            if (current > n || lock % 3 == except)
                return;
        }
    }
}

class ZeroEvenOdd2 {
    private int n;

    private volatile int current = 1;
    private volatile int idx = 0;

    private ReentrantLock lock = new ReentrantLock();
    private Condition zeroCondition = lock.newCondition();
    private Condition oddCondition = lock.newCondition();
    private Condition evenCondition = lock.newCondition();

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        for (int i = 0; i < n; i++) {
            if (idx != 0) {
                zeroCondition.await();
            }

            printNumber.accept(0);
            if (current % 2 == 1) { // 奇数
                idx = 1;
                oddCondition.signal();
            } else {
                idx = 2;
                evenCondition.signal();
            }
        }
        lock.unlock();
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        for (int i = 0; i < n; i = i + 2) {
            if (idx != 1) {
                oddCondition.await();
            }

            printNumber.accept(current);
            current++;
            idx = 0;
            zeroCondition.signal();
        }
        lock.unlock();
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        for (int i = 1; i < n; i = i + 2) {
            if (idx != 2) {
                evenCondition.await();
            }

            printNumber.accept(current);
            current++;
            idx = 0;
            zeroCondition.signal();
        }
        lock.unlock();
    }

}

class IntConsumerService implements IntConsumer {

    @Override
    public void accept(int value) {
        System.out.print(value);
    }
}

public class Solution1116 extends ValueMainClass {

    public static void main(String[] args) throws IOException, InterruptedException {
        int n = 9;
        ZeroEvenOdd2 handler = new ZeroEvenOdd2(n);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    handler.zero(new IntConsumerService());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aaaaaaa").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    handler.even(new IntConsumerService());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aaaaaab").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    handler.odd(new IntConsumerService());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aaaaaac").start();

    }

}
