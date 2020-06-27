package com.gavin.thread_demo.advance;

import java.util.concurrent.CountDownLatch;

/**
 *
 * 1. CountDownLatch 的使用
 * 2. 可以使用 join 达到 CountDownLatch 的同样效果
 *
 */
public class CountDownLatchAEntry {
    public static void main(String[] args) {
        usingJoin();
        usingCountDownLatch();
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);

        for (int i=0; i<threads.length; i++){

            threads[i] = new Thread(() -> {
//                for (int j=0; j<10000; j++){
//                    int result = 0;
//                    result += j;
//                }
                //  to do something
                countDownLatch.countDown();
            });
        }

        for (int i=0; i<threads.length; i++){
            threads[i].start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("latch end");
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];

        for (int i=0; i<threads.length; i++){

            threads[i] = new Thread(() -> {
            });
        }

        for (int i=0; i<threads.length; i++){
            threads[i].start();
        }

        for (int i=0; i<threads.length; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("join end");
    }
}
