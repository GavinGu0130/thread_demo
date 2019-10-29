package com.gavin.thread_demo.future_nearly_demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTask_Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> fTask = new FutureTask<>(()-> {
            Thread.sleep(3000);
            return 1+2;
        });

        ExecutorService es = Executors.newCachedThreadPool();

        es.submit(fTask);

        System.out.println("completed action to submit task!");
        // .get() 阻塞
        System.out.println(fTask.get());
        System.out.println("get result!");


        
    }
}
