package com.zkj.javastudy.concurrency.threadpool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class SimpleThreadPool extends Thread implements ThreadPool{
    private final int initSize;
    private final int maxSize;
    private final int coreSize;
    private int activeCount;

    private final ThreadFactory factory;
    private final RunableQueue runableQueue;
    private volatile boolean isShutdown=false;

    //要想控制线程池中执行任务的线程。必须有一个保存活跃线程的属性
    private final Queue<ThreadTask> threadQueue=new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY=new DenyPolicy.DiscardDenyPolice();
    private final static ThreadFactory DEFAULT_THREAD_FACTORY=new DefualtThreadFactory();
    private final long livetime;
    private final TimeUnit timeUnit;
    public SimpleThreadPool(int initSize,int maxSize,int coreSize,int queuesize){
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queuesize, DEFAULT_DENY_POLICY, 10 ,TimeUnit.SECONDS);
    }

    public SimpleThreadPool(int initSize,int maxSize,int coreSize,ThreadFactory threadFactory,int queuesize,DenyPolicy denyPolicy,long alivetime,TimeUnit timeUnit){
        this.initSize=initSize;
        this.maxSize=maxSize;
        this.coreSize=coreSize;
        this.factory=threadFactory;
        this.runableQueue=new LinkedRunableQueue(queuesize, denyPolicy, this);
        this.livetime=alivetime;
        this.timeUnit=timeUnit;
        this.init();
    }
    private void init(){
        start();
        for(int i=0;i<initSize;i++){
            newThread();
        }
    }
    private void newThread(){//用于一个一个加用于处理任务的线程数量
        InternalTask internalTask=new InternalTask(runableQueue);
        Thread thread=this.factory.createThread(internalTask);
        ThreadTask task=new ThreadTask(thread,internalTask);
        threadQueue.offer(task);
        this.activeCount++;
        thread.start();
    }

    private void removeThread(){
        ThreadTask threadTask=threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }
    @Override
    public void run() {
       //用于维护线程数量
       while(!isShutdown&&!isInterrupted()){
           try {
               timeUnit.sleep(livetime);
           } catch (Exception e) {
               isShutdown=true;
               break;
           }
           synchronized(this){
               if(isShutdown) break;
               //当前任务大于0，且活跃线程未到核心线程，继续扩容
               if(runableQueue.size()>0&&activeCount<coreSize){
                   for(int i=initSize;i<coreSize;i++){
                       newThread();
                   }
                   continue;//跳出循环。避免一下扩充到最大maxSize
               }
               if(runableQueue.size()>0&&activeCount<maxSize){
                   for(int i=coreSize;i<maxSize;i++){
                       newThread();
                   }
               }
               if(runableQueue.size()==0&&activeCount>coreSize){
                   for(int i=coreSize;i<activeCount;i++){
                       removeThread();
                   }
               }
           }
        }
    }
//线程池的销毁
    @Override
    public void shutdown() {
        // TODO Auto-generated method stub
        synchronized(this){
            if(isShutdown) return;
            isShutdown=true;
            threadQueue.forEach((t) ->{
                t.internalTask.stop();
                t.thread.interrupt();
            } );
            this.interrupt();
        }    
    }

    @Override
    public int getInitSize() {
        if(isShutdown)throw new IllegalStateException();
        return this.initSize;
    }
    @Override
    public int getMaxSize() {
        if(isShutdown)throw new IllegalStateException();
        return this.maxSize;
    }
    @Override
    public int getCoreSize() {
        if(isShutdown) throw new IllegalStateException();
        return this.coreSize;
    }
    @Override
    public int getQueueSize() {
        if(isShutdown) throw new IllegalStateException();
        return runableQueue.size();
    }
    @Override
    public int getActiveCount() {
        synchronized(this){
            return this.activeCount;
        }
    }
    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    @Override
    public void execute(Runnable runnable) {
        if(isShutdown) throw new IllegalStateException();
        runableQueue.offer(runnable);
        
    }
}
class ThreadTask{
    Thread thread;
    InternalTask internalTask;
    public ThreadTask(Thread thread,InternalTask task){
        this.thread=thread;
        this.internalTask=task;
    }
}