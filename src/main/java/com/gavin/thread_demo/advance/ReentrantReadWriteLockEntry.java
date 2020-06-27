package com.gavin.thread_demo.advance;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockEntry {

    static ReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();

    public static void main(String[] args) {
        for (int i=0; i<18; i++){
            new Thread(()-> read(readLock)).start();
        }

        for (int i=0; i<2; i++){
            new Thread(()-> write(writeLock)).start();
        }

    }

    public static void read(Lock lock){
        try{
            lock.lock();
            //  模拟业务执行时间
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock){
        try{
            lock.lock();
            //  模拟业务执行时间
            TimeUnit.SECONDS.sleep(1);
            System.out.println("write over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
