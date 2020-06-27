package com.gavin.thread_demo.base;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 * 场景：比较 synchronized & Atomic & LongAdder
 * 结论(高并发场景下的性能)：LongAdder > Atomic > Synchronized
 * 原因；
 * 1. LongAdder 使用分段锁技术 => 划分若干块 + 最终汇总
 * 2. Atomic 使用 CAS（无锁方案）
 *
 */
public class CompareSyncEntry {
    static long longCount = 0L;
    final static Object lock = new Object();
    static AtomicInteger atomicCount = new AtomicInteger(0);
    static LongAdder longAdderCount = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for (int i=0; i<threads.length; i++){
            threads[i] = new Thread(() -> {
                for (int j=0; j<100000; j++){
                    atomicCount.incrementAndGet();
                }
            });
        }

        long start = System.currentTimeMillis();
        for (Thread t : threads){
            t.start();
        }
        for (Thread t : threads){
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("Atomic: " + atomicCount + "; Time: " + (end-start));


        for (int i=0; i<threads.length; i++){
            threads[i] = new Thread(() -> {
                for (int j=0; j<100000; j++){
                    synchronized (lock){
                        longCount++;
                    }
                }
            });
        }

        start = System.currentTimeMillis();
        for (Thread t : threads){
            t.start();
        }
        for (Thread t : threads){
            t.join();
        }
        end = System.currentTimeMillis();
        System.out.println("Sync: " + longCount + "; Time: " + (end-start));



        for (int i=0; i<threads.length; i++){
            threads[i] = new Thread(() -> {
                for (int j=0; j<100000; j++){
                    longAdderCount.increment();
                }
            });
        }

        start = System.currentTimeMillis();
        for (Thread t : threads){
            t.start();
        }
        for (Thread t : threads){
            t.join();
        }
        end = System.currentTimeMillis();
        System.out.println("LongAdder: " + longAdderCount + "; Time: " + (end-start));
    }
}
