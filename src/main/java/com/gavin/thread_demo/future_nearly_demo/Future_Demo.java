package com.gavin.thread_demo.future_nearly_demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Future_Demo {



//    // 创建Result对象r
//    Result r = new Result();
//r.setAAA(a);
//    // 提交任务
//    Future<Result> future =
//            executor.submit(new Task(r), r);
//    Result fr = future.get();
//// 下面等式成立
//    fr === r;
//fr.getAAA() === a;
//fr.getXXX() === x
//
//    class Task implements Runnable{
//        Result r;
//        //通过构造函数传入result
//        Task(Result r){
//            this.r = r;
//        }
//        void run() {
//            //可以操作result
//            a = r.getAAA();
//            r.setXXX(x);
//        }
//    }

//    public static void main(String[] args){
//        ExecutorService pool
//                = Executors.newFixedThreadPool(1);
//        pool.submit(null, 1);
//
//    }
//
//    class Task implements Runnable{
//        String msg;
//        //通过构造函数传入result
//        Task(String p_Msg){
//            this.msg = p_Msg;
//        }
//        void run() {
//        }
//    }
}
