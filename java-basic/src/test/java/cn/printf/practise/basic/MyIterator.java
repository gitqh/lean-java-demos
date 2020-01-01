package cn.printf.practise.basic;

import org.junit.Test;

public class MyIterator {

    private String[] elements = {"hello", "world"};
    private int cursor = -1;

    public String next() {
        cursor++;
        return elements[cursor];
    }

    public boolean hasNext() {
        return (cursor + 1) < elements.length;
    }
}
