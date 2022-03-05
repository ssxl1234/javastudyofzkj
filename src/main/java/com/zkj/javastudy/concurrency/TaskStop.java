package com.zkj.javastudy.concurrency;

import java.util.concurrent.TimeUnit;
/**
 * 优雅的终止一个线程可以通过一个活动标志以及中断响应来控制线程的运行
 */
public class TaskStop extends Thread{
    private volatile boolean closed=false;
    @Override
    public void run() {
        System.out.println("begin working...");
        while(!closed&&!isInterrupted()){

        }
        System.out.println("Exiting....");
    }
    public void close(){
        closed=true;
        interrupt();
    }
    public static void main(String[] args) {
        TaskStop task=new TaskStop();
        task.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("now will stop work");
        task.close();

    }
}