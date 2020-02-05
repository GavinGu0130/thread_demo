package com.gavin.thread_demo.future_nearly_demo;

import java.util.concurrent.*;

public class FutureTaskEntry {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(()-> {
            System.out.println("Entry task");
            ThreadCommon.sleep(3, TimeUnit.SECONDS);
            return 2+1;
        });

        // 创建线程池
        ExecutorService es = Executors.newCachedThreadPool();
        // 提交FutureTask
        ThreadCommon
        es.submit(futureTask);

        //  当前任务状态
        System.out.println("The status of task is " + futureTask.isDone() );
        
        // 获取计算结果
        Integer result = futureTask.get();

        System.out.println(result);
    }
}
