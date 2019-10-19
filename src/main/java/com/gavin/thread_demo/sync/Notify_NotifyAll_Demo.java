package com.gavin.thread_demo.sync;

import java.util.LinkedList;
import java.util.List;

public class Notify_NotifyAll_Demo {
    private List<Object> controlList = new LinkedList<Object>();
    // 一次性申请所有资源
    synchronized void apply(    //  lock this
                                Object from, Object to){
        // 经典写法
        while(controlList.contains(from) ||
                controlList.contains(to)){
            try{
                System.out.println("The resource is used ,which the thread is ready to wait for the condition!");
                wait();    //  with this
            }catch(Exception e){
            }
        }

        System.out.println("The condition is setted under control!");
        controlList.add(from);
        controlList.add(to);
    }
    // 归还资源
    synchronized void freeByNotifyAll(
            Object from, Object to){
        System.out.println("The thread is ready to release the condition by notifyAll()!");
        controlList.remove(from);
        controlList.remove(to);
        notifyAll();    //  with this
    }

    // 归还资源
    synchronized void freeByNotify(
            Object from, Object to){
        System.out.println("The thread is ready to release the condition by notify()!");
        controlList.remove(from);
        controlList.remove(to);
        notify();    //  with this
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("The demo start for Notify_NotifyAll_Demo!");

        Object lockA = new Object();
        Object lockB = new Object();

        Object lockC = new Object();
        Object lockD = new Object();
        Notify_NotifyAll_Demo instance = new Notify_NotifyAll_Demo();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> instance.apply(lockA, lockB)).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> instance.apply(lockC,lockD)).start();
        }

        Thread.sleep(2000);
        new Thread(() -> instance.freeByNotify(lockA, lockB)).start();

        Thread.sleep(2000);
        new Thread(() -> instance.freeByNotifyAll(lockA, lockB)).start();
    }
}
