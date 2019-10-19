package com.gavin.thread_demo.sync;

import java.util.LinkedList;
import java.util.List;

public class Sync_Wait_Notify_Demo {
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
    synchronized void free(
            Object from, Object to){
        System.out.println("The thread is ready to release the condition!");
        controlList.remove(from);
        controlList.remove(to);
        notifyAll();    //  with this
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("The demo start for Sync_Wait_Notify!");

        Object fromObj = new Object();
        Object toObj = new Object();
        Sync_Wait_Notify_Demo instance = new Sync_Wait_Notify_Demo();

        new Thread(() -> instance.apply(fromObj, toObj)).start();
        new Thread(() -> instance.apply(fromObj, toObj)).start();
        new Thread(() -> instance.free(fromObj, toObj)).start();
        Thread.sleep(2000);
        new Thread(() -> instance.apply(fromObj, toObj)).start();
    }
}
