package com.ailisha.thread.signal;

/**
 * Ref:
 * http://ifeve.com/java-threadlocal%E7%9A%84%E4%BD%BF%E7%94%A8/
 * ThreadLocal类允许我们创建只能被同一个线程读写的变量。因此，如果一段代码含有一个ThreadLocal变量的引用，即使两个线程同时执行这段代码，它们也无法访问到对方的ThreadLocal变量。
 * @author zengjx
 * @version $Id: ThreadLocalTask.java 2018/3/22 zengjx $
 */
public class ThreadLocalTask implements Runnable {

    //static final可以不要
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    @Override
    public void run() {
        threadLocal.set(String.valueOf(Math.random() * 100D));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadLocal.get());
    }

    public static void main(String[] args) {
        ThreadLocalTask task = new ThreadLocalTask();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
    }
}
