package com.gavin.thread_demo.framework_flow_demo;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceEntry {
    public static void main(String[] args) {

        // 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        // 创建CompletionService
        CompletionService<Integer> cs = new
                ExecutorCompletionService<>(executor);

//        cs.submit(()->{System.out.println("A");});
//        cs.submit(()->{System.out.println("B");});
//        cs.submit(()->{System.out.println("C");});

        System.out.println("That's all");
    }
}
