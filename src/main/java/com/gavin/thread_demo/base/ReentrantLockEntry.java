package com.gavin.thread_demo.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 1. ReentrantLock 的标准写法（finally必须，释放锁）
 * 2. 可重入
 *
 */
public class ReentrantLockEntry {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockEntry ins = new ReentrantLockEntry();

        new Thread(ins::syncMethodOne).start();
    }

    public void syncMethodOne(){
        try{
            lock.lock();
            System.out.println("I'm a syncMethodOne");

            //  模拟多线程并发
            for (int i=1; i<6; i++){
                System.out.println(i);
                if (i == 2){
                    System.out.println("ready to call syncMethodTwo (reentrant)");
                    syncMethodTwo();
                }
            }
        }finally {
            lock.unlock();
        }
    }

    public void syncMethodTwo(){
        try{
            lock.lock();
            try {
                //  模拟业务计算耗时
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I'm a syncMethodTwo");
        }finally {
            lock.unlock();
        }
    }
}
