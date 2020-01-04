package cn.printf.practise.basic;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsTest {

    @Test
    public void commonMethods() {
        List<String> strings = Arrays.asList("1", "2", "3");
        // 洗牌
        Collections.shuffle(strings);
        System.out.println(strings);


        Collections.reverse(strings);
        System.out.println(strings);


    }
}
