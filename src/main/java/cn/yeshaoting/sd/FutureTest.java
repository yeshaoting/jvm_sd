package cn.yeshaoting.sd;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

public class FutureTest {

    private static int sum = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int n = 100;
        ExecutorService worker = Executors.newFixedThreadPool(n);
        List<Callable<Integer>> tasks = Lists.newArrayList();
        for (int i = 0; i < 2000; i++) {
            int finalI = i;
            tasks.add(new Callable<Integer>() {
                @Override
                public Integer call() {
                    int value = 0;
                    try {
                        for (int j = 0; j <= 100; j++) {
                            Thread.sleep(10);
                            value = value + j;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.printf("%d, %s\tfinish current value=%d\n", finalI, Thread.currentThread().getName(), value);
                    return value;
                }
            });
        }

        List<Future<Integer>> all = worker.invokeAll(tasks);
        for (Future<Integer> f : all) {
            sum = sum + f.get();
        }
        System.out.printf("final sum=%d\n", sum);
        worker.shutdown();
    }
}
