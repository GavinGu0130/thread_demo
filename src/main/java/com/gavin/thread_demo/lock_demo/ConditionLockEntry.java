package com.gavin.thread_demo.lock_demo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//  以阻塞队列为例，说明条件变量（Condition）
public class ConditionLockEntry {
    final Lock lock = new ReentrantLock();
    // 条件变量：队列不满
    final Condition notFull = lock.newCondition();
    // 条件变量：队列不空
    final Condition notEmpty = lock.newCondition();

    // 队列大小
    final Integer ARRANGE_SIZE = 5;
    //  当前大小
    volatile Integer currentSize = 0;


    // 入队
    void enq(String x) throws InterruptedException {
        lock.lock();    //  可重入
        try {

            while (currentSize == ARRANGE_SIZE) {//队列已满
            // 等待队列不满
                notFull.await();
            }
            // 省略入队操作...
            currentSize++;
            //入队后,通知可出队
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // 出队
    void deq() {
        lock.lock();    //  可重入
        try {
            while (currentSize == 0) {  //队列已空
                // 等待队列不空
                notEmpty.await();
            }
            // 省略出队操作...
            currentSize--;
            //出队后，通知可入队
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
