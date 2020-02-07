package com.gavin.thread_demo.future_nearly_demo;

import java.util.Random;
import java.util.concurrent.*;

public class FutureTaskEntry {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建FutureTask
        FutureTask<String> futureTask = new FutureTask<>(()-> {
            System.out.println("Entry task");
            ThreadCommon.sleep(3, TimeUnit.SECONDS);
            return "I'm a return value";
        });

        // 创建线程池
        ExecutorService es = Executors.newCachedThreadPool();
        // 提交FutureTask
        es.submit(futureTask);

        //  当前任务完成状态
        ThreadCommon.sleep(1, TimeUnit.SECONDS);
        System.out.println("The status of task is " + futureTask.isDone() );

        //  cancel the task 
        boolean isCancel = false;
        if((new Random().nextInt(10)  % 2) == 1 ? true : false ) {
            System.out.println("I'm ready to cancel");
            futureTask.cancel(true);
            isCancel = futureTask.isCancelled();
            System.out.println("The status of task is " + isCancel);
        }



        // 获取计算结果
        if (!isCancel) {
            String result = futureTask.get();

            System.out.println(result);
        }
    }
}
