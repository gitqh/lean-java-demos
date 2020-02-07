package cn.printf.practise.basic.collections;

import org.junit.Test;

import java.util.ArrayDeque;

public class QueueTest {

    @Test
    public void arrayQueueTest() {
        ArrayDeque<Request> requests = new ArrayDeque<Request>();

        // 模拟一个取号，进入队列
        for (int i = 0; i < 10; i++) {
            final int number = i;
            requests.offer(new Request() {
                @Override
                public int genNumber() {
                    System.out.println("这是第" + number + "个号码");
                    return number;
                }
            });
        }

        // 出队操作
        dealWith(requests);
    }

    private void dealWith(ArrayDeque<Request> requests){
        System.out.println(requests.poll().genNumber());
    }

    interface Request {
        int genNumber();
    }
}
