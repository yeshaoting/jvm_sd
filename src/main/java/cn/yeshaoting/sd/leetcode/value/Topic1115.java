package cn.yeshaoting.sd.leetcode.value;

import java.io.IOException;

/*
    description:
    author: yeshaoting
    time: 2019-08-13 20:23
*/
public class Topic1115 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Solution1115_A handler = new Solution1115_A(7);

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