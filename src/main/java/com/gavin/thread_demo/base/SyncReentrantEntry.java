package com.gavin.thread_demo.base;

import java.util.concurrent.TimeUnit;

/**
 *
 * 证明 synchronized 是可重入的
 *
 */
public class SyncReentrantEntry {
    public static void main(String[] args) {
        SyncReentrantEntry ins = new SyncReentrantEntry();

        new Thread(ins::syncMethodOne).start();
    }

    public synchronized void syncMethodOne(){
        System.out.println("I'm a syncMethodOne");

        //  模拟多线程并发
        for (int i=1; i<6; i++){
            System.out.println(i);
            if (i == 2){
                System.out.println("ready to call syncMethodTwo (reentrant)");
                syncMethodTwo();
            }
        }
    }

    public synchronized void syncMethodTwo(){
        try {
            //  模拟业务计算耗时
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I'm a syncMethodTwo");
    }
}
