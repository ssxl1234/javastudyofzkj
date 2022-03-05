package com.zkj.javastudy.concurrency.hook;
import java.util.concurrent.TimeUnit;

/**
 * 一个钩子程序会在主线程完成，JVM即将推出时启动执行。
 * 通过Runtime可以向JVM注入多个钩子程序。钩子程序的作用：
 * 
 */
public class HookThread {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("The Hook-1 is running");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        },"HooK-1"));
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("The Hook-2 is runing");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        },"Hook-2"));
        System.out.println("Main thread will exit!");
        TimeUnit.SECONDS.sleep(1);
    }

}
