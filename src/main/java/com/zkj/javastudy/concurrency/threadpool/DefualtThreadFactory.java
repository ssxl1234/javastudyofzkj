package com.zkj.javastudy.concurrency.threadpool;

import java.util.concurrent.atomic.AtomicInteger;

public class DefualtThreadFactory implements ThreadFactory{
    private static final AtomicInteger GROUP_COUNT=new AtomicInteger(1);
    private static final ThreadGroup group=new ThreadGroup("SimpleThread");
    @Override
    public Thread createThread(Runnable runnable) {
        return new Thread(runnable);
    }
}
