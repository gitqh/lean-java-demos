package cn.printf.practise.basic;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyIteratorTest {

    @Test
    public void shouldOutputElementMyIterator() {
        MyIterator myIterator = new MyIterator();

        while (myIterator.hasNext()){
            System.out.println(myIterator.next());
        }
    }
}
