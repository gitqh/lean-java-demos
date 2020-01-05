package cn.printf.practise.basic.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Java 排序
 */
public class SortTest {

    @Test
    public void shouldBigInt() {
        Integer[] integers = {2,3,4,5,3,4,5};

        Arrays.sort(integers, Integer::compareTo);

        for (int i = 0; i < integers.length; i++) {
            System.out.println(integers[i]);
        }

    }
}
