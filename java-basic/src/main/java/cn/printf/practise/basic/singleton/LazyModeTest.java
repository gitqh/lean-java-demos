package cn.printf.practise.basic.singleton;


public class LazyModeTest {

    /**
     * 多运行几次就能看到差异
     * @param args
     */
    public static void main(String[] args) {
        LazyModeTestThread lazyModeTestThread = new LazyModeTestThread();
        LazyModeTestThread lazyModeTestThread2 = new LazyModeTestThread();

        lazyModeTestThread.start();
        lazyModeTestThread2.start();
    }
}


class LazyModeTestThread extends Thread {
    private LazyMode lazyMode;

    @Override
    public void run() {
        super.run();
        try {
            lazyMode = LazyMode.getInstanceV1();
            System.out.println(lazyMode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

