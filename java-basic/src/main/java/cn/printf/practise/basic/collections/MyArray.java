package cn.printf.practise.basic.collections;

import java.util.Iterator;

public class MyArray {

    private String[] elements = {"hello", "world"};

    private class MyIterator implements Iterator<String> {
        private int cursor = -1;

        public String next() {
            cursor++;
            return elements[cursor];
        }

        public boolean hasNext() {
            return (cursor + 1) < elements.length;
        }
    }

    public Iterator<String> iterator(){
        return new MyIterator();
    }
}
