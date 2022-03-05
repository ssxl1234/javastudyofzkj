package com.zkj.javastudy.designmodel;
/**
 * 意图：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 * 主要解决：一个全局使用的类频繁地创建与销毁。
 * 何时使用：当您想控制实例数目，节省系统资源的时候。
 * 如何解决：判断系统是否已经有这个单例，如果有则返回，如果没有则创建。
 * 关键代码：构造函数是私有的。
 */

/**
*  单例模式有多种实现方式。其中分为饿汉式和懒汉式
*   饿汉式：类加载时就初始化。浪费内存。优点：不加锁，效率高，实现简单。
*   懒汉式：需要时才初始化。需要考虑线程安全问题。
*/
//饿汉式线程安全1：
public class SingleModel {
    private static SingleModel instance=new SingleModel();
    //私有构造函数。不允许外部调用
    private SingleModel(){

    }
    //获取唯一可用的实例
    public static SingleModel getInstance(){
        return instance;
    }
    public void dosomething(){
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        SingleModel.getInstance().dosomething();
    }
}
//饿汉式线程安全2：
class SingleModel_1{
    private volatile static SingleModel_1 instance;
    private SingleModel_1(){

    }
    public static SingleModel_1 getInstance(){
        if (instance==null){
            synchronized(SingleModel_1.class){
                if(instance==null){
                instance=new SingleModel_1();
                }
            }
        }
        return instance;
    }
}

//

//懒汉式线程安全1：
class LazySingleModel{
    private static LazySingleModel instance;
    private LazySingleModel(){

    }
    public static synchronized LazySingleModel getInstance(){
        if(instance==null){
            instance=new LazySingleModel();
        }
        return instance;
    }
}
