package com.gavin.thread_demo.threadlocal_demo;

import java.util.Collections;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

/**
 *
 * ThreadLocal 保证数据在不同线程中有不同的副本
 * 个人理解：
 * 1. 每个 Thread 会持有一个 ThreadLocalMap
 * 2. 每个 ThreadLocal 作为当前持有它的 Thread 的 ThreadLocalMap 的 key 存在
 *
 * 使用场景
 * 1. 声明式事务，保证同一个线程同一个Connection【同一线程中的多个业务（方法），会加到同一个事务中】
 *
 */
public class ThreadLocalEntry {
//    volatile static User user  = new User("gavin");
    static ThreadLocal<User> threadLocal = new ThreadLocal<>();
    static ThreadLocal<String> threadLocalB = new ThreadLocal<>();

    public static void main(String[] args) {


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadLocal.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.set(new User());
            threadLocalB.set("gavin");
            System.out.println(threadLocal.get());
            System.out.println(threadLocalB.get());
        }).start();
    }
}

class User{
    public User(String p_Name){
        this.name = p_Name;
    }

    public User (){}
    public String name = "gavin";

}