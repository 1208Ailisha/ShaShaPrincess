package com.ailisha.thread.signal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zengjx
 * @version $Id: ThreadLocalCalcTask.java 2018/3/26 zengjx $
 */
public class ThreadLocalCalcTask {
    private static ThreadLocal<Integer> threadLocal =
            new ThreadLocal<Integer>() {
                private Random random = new Random(10);

                //覆盖了ThreadLocal中的initialValue方法
                protected synchronized Integer initialValue() {
                    return random.nextInt(1000);
                }
            };

    public static void increament() {
        threadLocal.set(threadLocal.get() + 1);
    }

    public static Integer get() {
        return threadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Accessor(i));
        }
        TimeUnit.MILLISECONDS.sleep(300);  //300ms
        executorService.shutdownNow();
    }
}

class Accessor implements Runnable {

    private final int id;

    public Accessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalCalcTask.increament();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id + ":" + ThreadLocalCalcTask.get();
    }
}
