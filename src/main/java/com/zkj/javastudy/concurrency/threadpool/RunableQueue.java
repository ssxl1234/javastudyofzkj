package com.zkj.javastudy.concurrency.threadpool;
//用于存放提交到线程池的任务
public interface RunableQueue {
    void offer(Runnable task);
    Runnable take();
    int size();
}
