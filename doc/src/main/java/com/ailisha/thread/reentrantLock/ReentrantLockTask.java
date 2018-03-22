package com.ailisha.thread.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Ref:
 * https://my.oschina.net/hosee/blog/607677
 * @author zengjx
 * @version $Id: ReentrantLockTask.java 2018/3/21 zengjx $
 */
public class ReentrantLockTask implements Runnable {

    //public static reentrantLock lock = new reentrantLock();
    private ReentrantLock lock = new ReentrantLock();

    public static int i = 0;

    @Override
    public void run() {
        for (int index = 0; index < 100000; index++) {
            lock.lock();
            lock.lock();//如果拥有锁的某个线程再次得到锁，那么获取计数器就加1，然后锁需要被释放两次才能获得真正释放(重入锁)
            try {
                i++;
            } finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTask test = new ReentrantLockTask();
        Thread t1 = new Thread(test, "t1");
        Thread t2 = new Thread(test, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
