package com.zkj.javastudy.concurrency.threadpool;
//用于创建线程的工厂
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
