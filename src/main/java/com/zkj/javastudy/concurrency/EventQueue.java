package com.zkj.javastudy.concurrency;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class EventQueue {
    private final int max;
    static class Event{

    }
    private final LinkedList<Event> eventQueue=new LinkedList<>();
    private final static int DEFAULT_MAX=10;
    public EventQueue(){
        this(DEFAULT_MAX);
    }
    public EventQueue(int num){
        this.max=num;
    }
    public void offer(Event event){
        synchronized(eventQueue){
            while(eventQueue.size()>=max){
                try {
                    System.out.println("This Quene is Full,can't add any more!!!");
                    eventQueue.wait();
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
            eventQueue.add(event);
            System.out.println("add Event.total="+eventQueue.size());
            eventQueue.notify();
        }
    }

    public Event take(){
        synchronized(eventQueue){
            while(eventQueue.isEmpty()){
                System.out.println("Empty,No Event to get!!!!");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Event event=eventQueue.removeFirst();
            System.out.println("get an Event,Beging operate!!");
            eventQueue.notify();
            return event;
        }
    }
    public static void main(String[] args) {
        final EventQueue queue=new EventQueue();
        new Thread(()->{
            while(true){
                queue.offer(new EventQueue.Event());
            }
        },"Producer1").start();
        new Thread(()->{
            while(true){
                queue.offer(new EventQueue.Event());
            }
        },"Producer2").start();
        new Thread(()->{
            while(true){
                queue.take();
                try {
                    System.out.println("Begin to Operated Event!!!");
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
        },"Consumer").start();
    }
}
