package com.ailisha.thread.signal;

import java.util.concurrent.CountDownLatch;

/**
 * Ref:
 * http://www.importnew.com/15731.html
 * https://www.cnblogs.com/uodut/p/6830939.html
 * http://ifeve.com/talk-concurrency-countdownlatch/
 *
 * @author zengjx
 * @version $Id: CountDownLatchTask.java 2018/3/22 zengjx $
 */
public class CountDownLatchTask {
    public static void main(String[] args) {

        int threadCount = 10;
        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程" + Thread.currentThread().getId() + "开始出发");
                    /*try {
                        //Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    System.out.println("线程" + Thread.currentThread().getId() + "已到达终点");
                    latch.countDown();
                }
            }).start();
;
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("10个线程已经执行完毕！开始计算排名");
    }
}
