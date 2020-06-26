package com.gavin.thread_demo.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEntry {
    AtomicInteger count = new AtomicInteger(0);

    public void increase(){
        for (int i=0; i< 10000; i++){
            count.incrementAndGet();  // == count++
        }
    }

    public static void main(String[] args) {
        AtomicEntry ins = new AtomicEntry();
        List<Thread> threads = new ArrayList<>();

        for (int i=0; i<10; i++){
            threads.add(new Thread(ins::increase, "thread - " + i));
        }

        //  to start all threads in {threads}
        threads.forEach((o) -> o.start());

        //  to ensure that all increases have been performed
        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(ins.count);
    }
}
