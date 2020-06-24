package com.gavin.thread_demo.startup;

import org.springframework.aop.ThrowsAdvice;

import java.util.Random;

public class StartUpEntry {
    public static void main(String[] args) {
        //    first way
        new MyThread().start();
        //    second way
        new Thread(new MyRun()).start();
        //    third way
        new Thread(() -> {
            int count = new Random().nextInt(5);
            try {
                Thread.sleep(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("This is one way to start by lambda");
        }).start();
    }

    static class MyThread extends Thread{
        @Override
        public void run(){
            int count = new Random().nextInt(5);
            try {
                Thread.sleep(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("This is one way to start by extend Thread");
        }
    }

    static class MyRun implements Runnable{
        @Override
        public void run() {
            int count = new Random().nextInt(5);
            try {
                Thread.sleep(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("This is one way to start by implements Runnable");
        }
    }
}
