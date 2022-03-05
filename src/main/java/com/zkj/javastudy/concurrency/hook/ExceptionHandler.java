package com.zkj.javastudy.concurrency.hook;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.TimeUnit;

/**
 * 线程独立调度run方法不使用trycatch的话，抛出的异常直达jvm。创建线程的父线程无法获得子线程运行中出现的异常。
 * java 提供了UncaughtExceptionHandler()机制。允许线程出现异常时回调该接口
 * 从而获得线程异常信息和错误信息。
 * 其调用逻辑：
 *  if currentThread has handler
 *         handle :使用自己的处理器
 *  else if currentThread.getThreadGroup() has handler
 *          handle：使用组处理器
 *  else if group.parent!=null has handler
 *          handle：使用父祖处理器
 *  else Thread.getdefalutHandler!=null
 *          handler：使用默认处理器
 *  else system.err：直接输出到命令行
 *  
 */
public class ExceptionHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                // TODO Auto-generated method stub
                System.out.println(t.getName()+" occuer Exception");
                e.printStackTrace();
            }
        });
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    //TODO: handle exception
                }
                System.out.println(1/0);
            }
        },"ZeroDiv");
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                // TODO Auto-generated method stub
                System.out.println("got  an error");
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
