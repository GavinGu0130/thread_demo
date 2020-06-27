package com.gavin.thread_demo.base;

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
        System.out.println("ready to call syncMethodTwo (reentrant)");
        syncMethodTwo();
    }

    public synchronized void syncMethodTwo(){
        System.out.println("I'm a syncMethodTwo");
    }
}
