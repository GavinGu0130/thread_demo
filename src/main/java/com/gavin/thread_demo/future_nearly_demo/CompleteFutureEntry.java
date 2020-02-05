package com.gavin.thread_demo.future_nearly_demo;

import java.util.concurrent.CompletableFuture;

public class CompleteFutureEntry {
    public static void main(String[] args) {
        System.out.println("The time introduce 'CompletableFuture' start!");

        CompletableFuture<Void> cf_step_a = CompletableFuture.runAsync(()-> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("this is step a!");
        });

        CompletableFuture<String> cf_step_b = CompletableFuture.supplyAsync(()->{
            System.out.println("this is step b!");
            return  "step b has return value!";
        });

        CompletableFuture<String> cf_step_c = cf_step_a.thenCombine(cf_step_b, (__,pv)->{
            System.out.println(pv);
            System.out.println("this is step c!");

            return "end";
        });

        System.out.println(cf_step_c.join());
    }
}
