package com.gavin.thread_demo.future_nearly_demo.and_example;

import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.gavin.thread_demo.future_nearly_demo.ThreadCommon;

public class CompletableFutureWithNoParamAndNoRes {
    public static void main(String[] args) {
        //  1 -> 2a -> 3a    =>    1=runAsync + 2a=1.thenRunAsync + 3a=2a.thenRunAsync
        executeFuncOne();

        //  1 -> 2b -> 3b    =>    1=runAsync + 2b=runAsync + 3b=1.runAfterBothAsync(2b)
        executeFuncTwo();

        //  1 -> 2c -> 3c -> 4c    =>    1=runAsync + 2c=runAsync + 3c=1.runAfterBothAsync(2c) + 4c=3c.thenRunAsync
        executeFuncThree();

    }

    static void executeFuncOne(){
        CompletableFuture<Void> cf_1 = CompletableFuture.runAsync(()->{
            System.out.println("This is step 1 !");
            ThreadCommon.sleep(2, TimeUnit.SECONDS);
        });

        //  1 -> 2a -> 3a    =>    1=runAsync + 2a=1.thenRunAsync + 3a=2a.thenRunAsync
        CompletableFuture<Void> cf_2a = cf_1.thenRunAsync(()->{
            ThreadCommon.sleep(1, TimeUnit.SECONDS);
            System.out.println("This is step 2a !");
        });

        CompletableFuture<Void> cf_3a = cf_2a.thenRunAsync(()->{
            System.out.println("This is step 3a !");
        });

        cf_3a.join();
    }

    static void executeFuncTwo(){
        CompletableFuture<Void> cf_1 = CompletableFuture.runAsync(()->{
            System.out.println("This is step 1 !");
            ThreadCommon.sleep(2, TimeUnit.SECONDS);
        });

        //  1 -> 2b -> 3b    =>    1=runAsync + 2b=runAsync + 3b=1.runAfterBothAsync(2b)
        CompletableFuture<Void> cf_2b = CompletableFuture.runAsync(()->{
            ThreadCommon.sleep(1, TimeUnit.SECONDS);
            System.out.println("This is step 2b !");
        });


        CompletableFuture<Void> cf_3b = cf_1.runAfterBothAsync(cf_2b, ()->{
            System.out.println("This is step 3b !");
        });

        cf_3b.join();
    }

    static void executeFuncThree(){
        CompletableFuture<Void> cf_1 = CompletableFuture.runAsync(()->{
            System.out.println("This is step 1 !");
            ThreadCommon.sleep(2, TimeUnit.SECONDS);
        });

        //  1 -> 2c -> 3c -> 4c    =>    1=runAsync + 2c=runAsync + 3c=1.runAfterBothAsync(2c) + 4c=3c.thenRunAsync
        CompletableFuture<Void> cf_2c = CompletableFuture.runAsync(()->{
            System.out.println("This is step 2c !");
        });

        CompletableFuture<Void> cf_3c = cf_1.runAfterBothAsync(cf_2c, ()->{
            ThreadCommon.sleep(1, TimeUnit.SECONDS);
            System.out.println("This is step 3c !");
        });

        CompletableFuture<Void> cf_4c = cf_3c.thenRunAsync(()->{
            System.out.println("This is step 4c !");
        });

        cf_4c.join();
    }
}
