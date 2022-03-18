package com.zkj.javastudy.designmodel;
/**
 * 迭代器模式
 */
interface Iterator{
    boolean hasNext();
    Object next();
}
interface Iterable_1{
    Iterator getInIterator();
}
public class ItereratorModel implements Iterable_1{
    private String[] name={"zkj","HH","FK"};
    @Override
    public Iterator getInIterator() {
        return new NameIterator();
    }
    private class NameIterator implements Iterator{
        int index;
        @Override
        public boolean hasNext() {
            if(index<name.length) return true;
            return false;
        }
        @Override
        public Object next() {
            return name[index++];
        }
    }
    public static void main(String[] args) {
        ItereratorModel m=new ItereratorModel();
        Iterator i=m.getInIterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }

    }
}

