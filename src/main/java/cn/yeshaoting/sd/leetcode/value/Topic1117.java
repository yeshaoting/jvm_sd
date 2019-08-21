package cn.yeshaoting.sd.leetcode.value;

import java.util.concurrent.Semaphore;

/*
    description:
    author: yeshaoting
    time: 2019-08-13 19:36
*/
public class Topic1117 {

}


class Solution1117_A {

    private Semaphore hydrogenS = new Semaphore(2);
    private Semaphore oxygenS = new Semaphore(0);

    public Solution1117_A() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenS.acquire();

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();

        oxygenS.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenS.acquire(2);

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();

        hydrogenS.release(2);

    }
}