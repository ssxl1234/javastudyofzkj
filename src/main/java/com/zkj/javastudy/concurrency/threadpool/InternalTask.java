package com.zkj.javastudy.concurrency.threadpool;
/**
 * 该内部任务将不断从queue中拿出task，然后运行
 */
public class InternalTask implements Runnable{
    private final RunableQueue taskQueue;
    private volatile boolean running=true;  
    public InternalTask(RunableQueue runableQueue){
        taskQueue=runableQueue;
    }
    @Override
    public void run() {
        // 当前线程在运行且未被中断，不断的从任务中取任务并执行
        while(running&&!Thread.currentThread().isInterrupted()){
           try {
                Runnable task=taskQueue.take();
                task.run();
           } catch (Exception e) {
               running=false;
                break;          } 
        }
    }
    public void stop(){
        running=false;
    }
}
