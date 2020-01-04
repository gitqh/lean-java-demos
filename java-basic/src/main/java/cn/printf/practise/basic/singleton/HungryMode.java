package cn.printf.practise.basic.singleton;

public class HungryMode {
    private static HungryMode instance = null;

    /**
     * 避免外部直接初始话
     */
    private HungryMode() {

    }

    /**
     * 公共的对外方法
     */
    public static HungryMode getInstance() throws InterruptedException {
        if (null == instance) {
            // 放大多线程误差
            Thread.sleep(1000);
            instance = new HungryMode();
        }

        return instance;
    }
}
