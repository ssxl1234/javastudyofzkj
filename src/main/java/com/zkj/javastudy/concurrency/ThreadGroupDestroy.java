package com.zkj.javastudy.concurrency;

public class ThreadGroupDestroy {
    public static void main(String[] args) {
        ThreadGroup testgGroup=new ThreadGroup("test");
        ThreadGroup maingGroup=Thread.currentThread().getThreadGroup();
        maingGroup.list();
        System.out.println("test group is destoryed "+testgGroup.isDestroyed());
        testgGroup.destroy();;
        System.out.println("test group is destoryed "+testgGroup.isDestroyed());
        maingGroup.list();
    }
}
