package com.gavin.thread_demo.advance;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 使用
 * 1. 不需要锁对象，直接锁等待.
 * 2. 指定解锁线程
 * 3. unpark() 可以先于 park() 调用 => 线程不用阻塞
 *
 */
public class LockSupportEntry {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i=0; i<10; i++){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
                if (i==3){
                    LockSupport.park();
                }
            }
        });

        thread.start();

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ready to unpark, after 6 second");
        LockSupport.unpark(thread);
    }
}
