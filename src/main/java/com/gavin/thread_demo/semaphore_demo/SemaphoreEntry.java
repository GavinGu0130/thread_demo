package com.gavin.thread_demo.semaphore_demo;

import java.util.concurrent.Semaphore;

public class SemaphoreEntry {
    private Semaphore sem = new Semaphore(3);
    private Semaphore sem1 = new Semaphore(3);
    //  不释放限流资源
    public void printByControl(String show) throws InterruptedException {
        sem.acquire();
        System.out.println(show);
    }

    public void printByRelease(String show) throws InterruptedException {
        sem1.acquire();
        System.out.println(show);
        sem1.release();
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreEntry instance = new SemaphoreEntry();
        for (int i =0;i < 5; i++){
            instance.printByRelease("Release" + String.valueOf(i));
        }
        for (int i =0;i < 5; i++){
            instance.printByControl(String.valueOf(i));
            //  阻塞
        }
    }
}
