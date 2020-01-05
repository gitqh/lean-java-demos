package cn.printf.practise.basic.singleton;

/**
 * 饿汉式单例，内部类模式，类加载不会初始话，使用时才初始话
 */
public class HungryModeV2 {
    private static class HungryModeV2Holder {
        private static HungryModeV2 instance = new HungryModeV2();
    }

    /**
     * 避免外部直接初始话
     */
    private HungryModeV2() {

    }

    /**
     * 公共的对外方法
     */
    public static HungryModeV2 getInstance() throws InterruptedException {
        return HungryModeV2Holder.instance;
    }
}
