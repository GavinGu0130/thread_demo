package com.gavin.thread_demo.framework_flow_demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CountDownLatchEntry {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDown = new CountDownLatch(3);
        Executor exec = Executors.newFixedThreadPool(3);

        exec.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("step A is running !");

            countDown.countDown();
        });

        exec.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("step B is running !");
            countDown.countDown();
        });

        exec.execute(() -> {
            System.out.println("step C is running !");

            countDown.countDown();
        });

//        exec.execute(() -> {
//            System.out.println("step D is running !");
//
//            countDown.countDown();
//        });

        countDown.await();
        System.out.println("All steps is done !");
    }
}
