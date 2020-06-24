package com.gavin.thread_demo;

import com.gavin.thread_demo.future_nearly_demo.ThreadCommon;

import java.sql.Time;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.concurrent.*;

public class ThreadPoolExecuterEntry {
    public static void main(String[] args) {
        BlockingQueue workQueue = new LinkedBlockingQueue<>(2);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4,10,30000, TimeUnit.SECONDS, workQueue);

        for (int i=0;i < 4; i++){
            pool.execute(()-> System.out.println("The number is "));
        }


//        for (int i=0;i < 4; i++){
//            pool.submit((s) -> {System.out.println("The number is " + s);}, i);
//        }

//        pool.submit((r)->{System.out.println(r);}, "I'm a param");

    }
}