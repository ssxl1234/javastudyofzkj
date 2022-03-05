package com.zkj.javastudy.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {
    private Thread currentThread;
    private List<Thread> blockedThreads=new ArrayList<>();
    private volatile boolean locked=false;
    @Override
    public List<Thread> getBlockThreads() {
        // TODO Auto-generated method stub
        return blockedThreads;
    }

    @Override
    public void lock() throws InterruptedException {
        // TODO Auto-generated method stub
        synchronized(this){
            while(locked){
                Thread temp=Thread.currentThread();
                blockedThreads.add(temp);
                try {
                    //存在问题。如果一个获得锁的线程被别的线程所中断。那么blockedThreads中依然回保存该线程对象。可以增加一个相应中断的代码块
                    wait();
                } catch (InterruptedException e) {
                    //TODO: handle exception
                    blockedThreads.remove(temp);
                    throw e;
                }
                
            }
            blockedThreads.remove(Thread.currentThread());
            locked=true;
            currentThread=Thread.currentThread();
        }
    }

    @Override
    public void lock(long tmills) throws InterruptedException, TimeoutException {
        // TODO Auto-generated method stub
        synchronized(this){
            if(tmills<=0){
                lock();
            }else{
                long remainstime=tmills;
                long endtime=System.currentTimeMillis()+remainstime;
                while(locked){
                    if(remainstime<=0){
                        throw new TimeoutException();
                    }
                    if(!blockedThreads.contains(Thread.currentThread())){
                        blockedThreads.add(Thread.currentThread());
                    }
                    wait(remainstime);
                    remainstime=endtime-System.currentTimeMillis();
                }
                blockedThreads.remove(Thread.currentThread());
                locked=true;
                currentThread=Thread.currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        // TODO Auto-generated method stub
        synchronized(this){
            if(currentThread==Thread.currentThread()){
                locked=false;
                notify();
            }
        }
    }

}
