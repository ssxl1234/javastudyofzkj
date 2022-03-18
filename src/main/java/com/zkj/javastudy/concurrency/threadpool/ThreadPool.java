package com.zkj.javastudy.concurrency.threadpool;

public interface ThreadPool {
    //提交任务到线程池
    void execute(Runnable runnable);
    //关闭线程
    void shutdown();
    //获取初始化线程池大小
    int getInitSize();
    //获取最大线程数
    int getMaxSize();
    //获取核心线程数
    int getCoreSize();
    //获取任务队列大小
    int getQueueSize();
    //获得线程活跃线程数量
    int getActiveCount();
    //判断线程池是否关闭
    boolean isShutdown();
}
