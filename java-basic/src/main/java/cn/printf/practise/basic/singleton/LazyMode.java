package cn.printf.practise.basic.singleton;

public class LazyMode {

    private static LazyMode instance = null;

    /**
     * 避免外部直接初始话
     */
    private LazyMode() {

    }

    /**
     * 线程不安全版本
     */
    public static LazyMode getInstanceV1() throws InterruptedException {
        if (null == instance) {
            Thread.sleep(1000);
            instance = new LazyMode();
        }

        return instance;
    }

    /**
     * 线程安全版本
     */
    public static LazyMode getInstanceV2() throws InterruptedException {

        synchronized (LazyMode.class) {
            if (null == instance) {
                Thread.sleep(1000);
                instance = new LazyMode();
            }
        }

        return instance;
    }


    /**
     * 线程安全版本，提高已经存在的效率，经典的双重检查模式
     */
    public static LazyMode getInstanceV3() throws InterruptedException {

        if (null == instance) {
            synchronized (LazyMode.class) {
                if (null == instance) {
                    Thread.sleep(1000);
                    instance = new LazyMode();
                }
            }
        }
        return instance;
    }
}
