package com.zkj.javastudy.concurrency.threadpool;

public class RunableDenyException extends Exception{
    public RunableDenyException(String message){
        super(message);
    }
}
