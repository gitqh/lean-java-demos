package cn.printf.practise.basic.collections;

import org.junit.Test;

public class CustomStackTest {

    @Test
    public void shouldBackHistoryByStack() {
        CustomStack<String> stringCustomStack = new CustomStack<String>(10);

        stringCustomStack.push("baidu.com");
        stringCustomStack.push("google.com");

        System.out.println("大小:" + stringCustomStack.size());

        for (int i = 0; i < stringCustomStack.size() + 1; i++) {
            System.out.println(stringCustomStack.pop());
        }
    }
}
