package com.gavin.thread_demo.future_nearly_demo.or_example;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureWithNoParamAndNoResByOr {
    public static void main(String[] args) {
//        CompletableFuture<Void> fAll = CompletableFuture.runAsync(()->System.out.println("this is step 1 !")).runaf
//                .runAfterEitherAsync(()->System.out.println("this is step 2 !"));
        CompletableFuture<Void> cf_1 = CompletableFuture.runAsync(()->{
            System.out.println("This is step 1 !");
        });

        CompletableFuture<Void> cf_2 = CompletableFuture.runAsync(()->{
            System.out.println("This is step 2 !");
        });

        CompletableFuture<Void> cf_3 = cf_1.runAfterEitherAsync(cf_2, ()->{
            System.out.println("This is step 3 !");
        });
    }
}
