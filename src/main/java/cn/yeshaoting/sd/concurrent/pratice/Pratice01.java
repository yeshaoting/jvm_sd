package cn.yeshaoting.sd.concurrent.pratice;

import cn.yeshaoting.sd.leetcode.value.Solution1114_E;

import java.io.IOException;

/*
    description:
    author: yeshaoting
    time: 2019-08-21 15:45
*/

/**
 * 启动3个线程:
 * 线程1 打印 1 2 3 4 5
 * 线程2 打印 6 7 8 9 10
 * 线程3 打印 11 12 13 14 15
 * 依次循环 输出到75
 * <p>
 * 思路：使用一个可见变量 做累加让其它线程都能知道
 */
public class Pratice02 {

    public static void main(String[] args) throws IOException, InterruptedException {
        new PraticeTask02(0).start();
        new PraticeTask02(1).start();
        new PraticeTask02(2).start();
    }

}

class PraticeTask02 extends Thread {

    private final static int n = 75;

    private int idx = 0;

    public PraticeTask02(int idx) {
        this.idx = idx;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i * );
        }
    }
}
