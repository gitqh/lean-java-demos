package cn.printf.practise.basic.collections;

import java.util.ArrayDeque;
import java.util.Deque;




public class CustomStack<E> {

    // 容器
    private Deque<E> container  = new ArrayDeque<>();

    // 容量
    private int cap;

    public CustomStack(int cap) {
        super();
        this.cap = cap;
    }

    public boolean push(E e){
        if(container.size() + 1 > cap){
            return false;
        }

        return container.offerLast(e);
    }

    public E pop(){
        return container.pollLast();
    }

    public E peek(){
        return container.peekLast();
    }

    public int size(){
        return container.size();
    }
}
