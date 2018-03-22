package com.ailisha.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zengjx
 * @version $Id: CallablleTask.java 2018/3/22 zengjx $
 */
public class CallablleTask implements Callable<String> {

    private String message;

    public CallablleTask(String message) {
        this.message = message;
    }

    @Override
    public String call() throws Exception {
        //模拟isDone返回false
        Thread.sleep(1000);
        return message;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new CallablleTask("message content");
        FutureTask<String> task = new FutureTask<String>(callable);

        long beginTime = System.currentTimeMillis();
        new Thread(task).start();

        System.out.println("isDone:" + task.isDone());
        // 调用get()阻塞主线程，反之，线程不会阻塞
        String result = task.get();
        long endTime = System.currentTimeMillis();

        System.out.println("isDone:" + task.isDone());
        System.out.println("message:" + result);
        System.out.println("take time:" + (endTime - beginTime) / 1000 + " seconds");
    }
}
