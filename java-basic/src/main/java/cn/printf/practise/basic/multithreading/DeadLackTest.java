package cn.printf.practise.basic.multithreading;

/**
 * 死锁的演示
 */
public class DeadLackTest {

    public static void main(String[] args){
        Object goods = new Object();
        Object money = new Object();

        TestThread testThread = new TestThread(goods,money);
        Thread proxy = new Thread(testThread);

        TestThread2 testThread2 = new TestThread2(goods,money);
        Thread proxy2 = new Thread(testThread2);


        proxy.start();
        proxy2.start();
    }
}


class TestThread implements Runnable{

    Object goods = new Object();
    Object money = new Object();


    public TestThread(Object goods, Object money) {
        this.goods = goods;
        this.money = money;
    }

    @Override
    public void run() {

        try {
            while (true){
                test();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test() throws InterruptedException {
        synchronized (money){
            Thread.sleep(500);
            synchronized (goods){
            }
        }

        System.out.println("一手给钱");
    }
}


class TestThread2 implements Runnable{

    Object goods = new Object();
    Object money = new Object();

    public TestThread2(Object goods, Object money) {
        this.goods = goods;
        this.money = money;
    }

    @Override
    public void run() {

        try {
            while (true){
                test();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test() throws InterruptedException {
        synchronized (goods){
            Thread.sleep(500);
            synchronized (money){

            }
        }

        System.out.println("一手给货");
    }
}
