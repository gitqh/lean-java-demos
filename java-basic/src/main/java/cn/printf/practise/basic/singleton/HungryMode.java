package cn.printf.practise.basic.singleton;

/**
 * 饿汉式单例
 */
public class HungryMode {
    private static HungryMode instance = new HungryMode();

    /**
     * 避免外部直接初始话
     */
    private HungryMode() {

    }

    /**
     * 公共的对外方法
     */
    public static HungryMode getInstance() throws InterruptedException {
        return instance;
    }
}
