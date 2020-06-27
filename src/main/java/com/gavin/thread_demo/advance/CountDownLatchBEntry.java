package com.gavin.thread_demo.advance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * 使用 CountDownLatch
 * 适用场景：多个业务同时并发执行，没有先后顺序，等待直到所有业务执行完毕，再执行后续操作
 *
 */
public class CountDownLatchBEntry {

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
