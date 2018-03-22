package com.ailisha.thread.callable;

import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

/**
 * @author zengjx
 * @version $Id: BatchTask.java 2018/3/22 zengjx $
 */
public class BatchTask {

    static class Sum implements Callable<Long> {

        private final long from;
        private final long to;

        public Sum(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call() throws Exception {
            long acc = 0;
            for (long i = from; i <= to; i++) {
                acc = acc + i;
            }
            System.out.println(Thread.currentThread().getName() + " : " + acc);
            return acc;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Long>> results = executor.invokeAll(asList(new Sum(0, 10), new Sum(0, 100), new Sum(0, 1000)));
        executor.shutdown();

        for (Future<Long> result : results) {
            System.out.println(result.get());
        }
    }
}
