package cn.printf.practise.basic.collections;

import org.junit.Test;

import java.util.Iterator;

public class MyIteratorTest {

    @Test
    public void shouldOutputElementMyIterator() {
        MyArray myArray = new MyArray();
        Iterator<String> iterator = myArray.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
