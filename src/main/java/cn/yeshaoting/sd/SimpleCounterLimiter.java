package cn.yeshaoting.sd;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleCounterLimiter {

    private static final int MAX_COUNTER = 10;
    private static AtomicReference<Pair<Long, Integer>> counter = new AtomicReference(Pair.of(0, 0));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        executeTask();
    }

    private static Triple<Boolean, Long, Integer> limit() {
        while (true) {
            System.out.println("retry cas action");
            Pair<Long, Integer> pair = counter.get();
            long currentSeconds = System.currentTimeMillis() / 1000;

            Pair<Long, Integer> newPair;
            if (pair.getKey() > currentSeconds) {
                newPair = Pair.of(currentSeconds, 1);
            } else {
                newPair = Pair.of(pair.getKey(), pair.getValue() + 1);
            }

            System.out.println("fail hold cas3");
            boolean flag = counter.compareAndSet(pair, newPair);
            if (flag == false) {
                System.out.println("fail hold cas");
                continue;
            }

            if (newPair.getValue() > MAX_COUNTER) {
                System.out.println("fail hold cas2");
                return Triple.of(true, pair.getKey(), newPair.getValue());
            }

            System.out.println("fail hold cas1");
            return Triple.of(false, pair.getKey(), newPair.getValue());
        }
    }

    private static void executeTask() throws ExecutionException, InterruptedException {
        ExecutorService worker = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            final int v = i;
            Future<String> future = worker.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(100);

                    System.out.println("limit to here12");
                    Triple<Boolean, Long, Integer> triple = limit();
                    System.out.println("limit to here1");
                    String feature = triple.getLeft() ? "blocked" : "passed";
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    String result = String.format("%s\t%s\t%s\t %s-%s-%s", sdf.format(new Date()), triple.getMiddle(), triple.getRight(), v, Thread.currentThread().getName(), feature);
                    System.out.println(result);
                    return result;
                }
            });

        }
    }
}
