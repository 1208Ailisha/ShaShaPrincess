package com.ailisha.thread.signal;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Ref:
 * http://ifeve.com/concurrency-cyclicbarrier/
 * CyclicBarrier让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 * @author zengjx
 * @version $Id: CyclicBarrierTask.java 2018/3/22 zengjx $
 */
public class CyclicBarrierTask {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,new Task());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    System.out.println("thread message");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread message");
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("Task thread message");
        }
    }
}
