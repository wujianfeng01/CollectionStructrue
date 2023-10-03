package DesignMode.SingleMode.hungrymode;

/**
 * 枚举实现单例模式-饿汉
 */
public enum SingletonEnum {
    // 在类加载时就创建实例
    SINGLETON_ENUM;
    /**
     * 定义实例行为
     */
    public void doSomething(){
        System.out.println("singleton enum");
    }
}
