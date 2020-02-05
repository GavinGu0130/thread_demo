package com.gavin.thread_demo.future_nearly_demo;

import java.util.concurrent.TimeUnit;

public class ThreadCommon {
    public static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        }catch(InterruptedException e){}
    }
}
