package com.zkj.javastudy.concurrency.threadpool;

import java.util.LinkedList;

public class LinkedRunableQueue implements RunableQueue{
    private final int limit;
    private final DenyPolicy denyPolicy;
    private final LinkedList<Runnable> tasklist=new LinkedList<>();
    private final ThreadPool threadPool;
    public LinkedRunableQueue(int limit,DenyPolicy dp,ThreadPool tPool){
        limit=limit;
        denyPolicy=dp;
        threadPool=tPool;
    }
    @Override
    public void offer(Runnable task) {
        synchronized(tasklist){
            if(tasklist.size()>=limit) denyPolicy.reject(task, threadPool);
            else{
                tasklist.addLast(task);
                tasklist.notifyAll();
            }
        }
    }
    @Override
    public Runnable take() {
        synchronized(tasklist){
            while(tasklist.isEmpty()){
                try {
                    tasklist.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return tasklist.removeFirst();
    }
    @Override
    public int size() {
        synchronized(tasklist){
            return tasklist.size();
        }
      
    }
}
