package com.gavin.thread_demo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景描述：使用 volatile ，创建 10 个线程，每个线程执行 count++ 10000次
 * 期望结果：10 * 10000 = 10w
 * 最终结果：1 ～ 10w 区间随机
 * 原因：volatile 只保证可见性，不保证原子性
 * 1. volatile => 保证在获取时，可以拿到最新的值
 * 2. 同时有多个线程获取最新值 => 有多少个线程，最终就会少加多少的值
 * 解决方案：
 * void increase() => synchronized void increase()
 *
 */
public class VolatileEntry {
    volatile int count = 0;

    public /*synchronized*/ void increase(){
        for (int i=0; i< 10000; i++){
            count++;
        }
    }

    public static void main(String[] args) {
        VolatileEntry ins = new VolatileEntry();
        List<Thread> threads = new ArrayList<>();

        for (int i=0; i<10; i++){
            threads.add(new Thread(ins::increase, "thread - " + i));
        }

        //  to start all threads in {threads}
        threads.forEach((o) -> o.start());

        //  to ensure that all increases have been performed
        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(ins.count);
    }
}
