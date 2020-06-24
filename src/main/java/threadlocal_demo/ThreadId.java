package threadlocal_demo;

import java.util.concurrent.atomic.AtomicLong;

class ThreadId {
    static final AtomicLong nextId=new AtomicLong(0);
    //定义ThreadLocal变量
    static final ThreadLocal<Long> tl=ThreadLocal.withInitial(
            ()->nextId.getAndIncrement());
    //此方法会为每个线程分配一个唯一的Id
    static long get(){
        return tl.get();
    }

    public static void main(String[] args)  {
        Thread th1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(ThreadId.get());
            }
            });
        th1.start();
//        Thread th2 = new Thread(() -> {System.out.println(ThreadId.get());});
//        th2.start();
        System.out.println(ThreadId.get());
        System.out.println(ThreadId.get());
    }
}