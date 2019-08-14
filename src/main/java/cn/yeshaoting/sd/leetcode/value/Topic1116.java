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
class Solution1116_A {
    private int n;

    // 当前要输出的非0整数
    private volatile int current = 1;
    private volatile int lock = 0;

    public Solution1116_A(int n) {
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

class Solution1116_B {
    private int n;

    private volatile int idx = 0;

    private ReentrantLock lock = new ReentrantLock();
    private Condition zeroCondition = lock.newCondition();
    private Condition oddCondition = lock.newCondition();
    private Condition evenCondition = lock.newCondition();

    public Solution1116_B(int n) {
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
            if (i % 2 == 0) { // 奇数
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

            printNumber.accept(i + 1);
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

            printNumber.accept(i + 1);
            idx = 0;
            zeroCondition.signal();
        }
        lock.unlock();
    }

}

class Solution1116_C {
    private int n;

    private volatile int idx = 0;

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Solution1116_C(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        for (int i = 0; i < n; i++) {
            while (idx != 0) {
                condition.await();
            }

            printNumber.accept(0);
            if (i % 2 == 0) { // 奇数
                idx = 1;
            } else {
                idx = 2;
            }

            condition.signalAll();
        }
        lock.unlock();
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        for (int i = 0; i < n; i = i + 2) {
            while (idx != 1) {
                condition.await();
            }

            printNumber.accept(i + 1);
            idx = 0;
            condition.signalAll();
        }
        lock.unlock();
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        for (int i = 1; i < n; i = i + 2) {
            while (idx != 2) {
                condition.await();
            }

            printNumber.accept(i + 1);
            idx = 0;
            condition.signalAll();
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

public class Topic1116 {

    public static void main(String[] args) throws IOException, InterruptedException {
        int n = 9;
//        Solution1116_A handler = new Solution1116_A(n);
        Solution1116_B handler = new Solution1116_B(n);
//        Solution1116_C handler = new Solution1116_C(n);

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
