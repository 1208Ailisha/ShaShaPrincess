package com.ailisha.thread.signal;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Ref:
 * http://ifeve.com/concurrency-semaphore/
 * http://ifeve.com/thread-synchronization-utilities-2/
 * http://ifeve.com/thread-synchronization-utilities-3/
 * Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源
 * @author zengjx
 * @version $Id: SemaphoreTask.java 2018/3/22 zengjx $
 */
public class SemaphoreTask {
    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        //虽然有30个线程在执行，但是只允许10个并发的执行
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("process data");
                        semaphore.acquire();
                        System.out.println("save data");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }
            });
        }
    }
}
