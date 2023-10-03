package DesignMode.SingleMode.hungrymode;

/**
 * 单例模式饿汉
 */
public class Singleton {
    // 在类加载时就创建实例
    private static Singleton singleton = new Singleton();
    // 私有构造函数，防止外部实例化
    private Singleton(){}
    // 提供一个全局访问点以获取实例
    public static Singleton getSingleton(){
        return singleton;
    }

}
