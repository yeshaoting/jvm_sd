package cn.yeshaoting.sd;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleCounterLimiter {

    private static final int MAX_COUNTER = 10;
    private static AtomicReference<Pair<Long, Integer>> counter = new AtomicReference(Pair.of(0L, MAX_COUNTER + 1));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        executeTask();
    }

    private static Triple<Boolean, Long, Integer> limit() {
        while (true) {
            Pair<Long, Integer> pair = counter.get();
            long currentSeconds = System.currentTimeMillis() / 1000;

            Pair<Long, Integer> newPair;
            if (pair.getKey() < currentSeconds) {
                newPair = Pair.of(currentSeconds, 1);
            } else {
                newPair = Pair.of(pair.getKey(), pair.getValue() + 1);
            }

            boolean flag = counter.compareAndSet(pair, newPair);
            if (flag == false) {
                System.out.println("fail hold cas");
                continue;
            }

            if (newPair.getValue() > MAX_COUNTER) {
                return Triple.of(true, newPair.getKey(), newPair.getValue());
            }

            return Triple.of(false, newPair.getKey(), newPair.getValue());
        }
    }

    private static void executeTask() throws ExecutionException, InterruptedException {
        ExecutorService worker = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 300; i++) {
            final int v = i;
            worker.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Triple<Boolean, Long, Integer> triple = limit();
                    String feature = triple.getLeft() ? "blocked" : "passed";
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    String result = String.format("%s\t%s\t%s\t %s-%s-%s", sdf.format(new Date()), triple.getMiddle(), triple.getRight(), v, Thread.currentThread().getName(), feature);
                    System.out.println(result);
                }
            });

        }
        worker.shutdown();
    }
}
