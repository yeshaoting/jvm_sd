package cn.yeshaoting.sd.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentSumTest {

    private static final AtomicInteger counter = new AtomicInteger(0);
    private static int value2 = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService worker = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 2000; i++) {
            worker.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int value = counter.incrementAndGet();
                    value2++;
                    System.out.printf("current value=%d, value2=%d\n", value, value2);
                }
            });
        }

        worker.awaitTermination(5, TimeUnit.SECONDS);
        System.out.printf("final value=%d, value2=%d\n", counter.get(), value2);
        worker.shutdown();
    }
}
