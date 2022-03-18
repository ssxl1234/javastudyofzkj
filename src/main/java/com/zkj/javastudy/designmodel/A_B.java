package com.zkj.javastudy.designmodel;
class B{
    A a;
    public B(A a){
        System.out.println("B初始化");
        this.a=a;
    }
    public A get(){
        System.out.println("取 A");
        if(a==null) System.out.println("null A");
        return a;
    }
    public void set(A a){
        this.a=a;
    }
}
class A {
    B b;
    public A(B b){
        System.out.println("A初始化");
        this.b=b;
    }
}
public class A_B {
    public static void main(String[] args) {
        A a=null;
        B b=new B(a);
        a=new A(b);
        // a=b.get();
        b.set(a);
        b.get();
    }
}
