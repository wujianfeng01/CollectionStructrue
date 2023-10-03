package DesignMode.SingleMode.LazyMode;

/**
 * 懒汉模式-双重校验锁
 * 1、线程安全性：在多线程环境下，如果不进行双重校验，多个线程可能会同时通过第一次检查
 *   并且在第二次检查之前都会进入同步块，这会导致多个实例被创建。
 *   违背了单例模式的初衷。使用双重校验可以确保只有一个线程能够成功创建实例。
 *
 * 2、性能优化：双重校验允许我们只在第一次创建实例时才获取同步锁。
 *   一旦实例被创建，后续的线程不需要再进入同步块。
 *   从而提高了程序的性能。如果不使用双重校验，每次获取实例都需要进入同步块，会导致性能下降。
 *
 * 3、避免不必要的实例创建：双重校验通过第一次检查避免了不必要的实例创建。
 */
public class SingletonbyLazy {
    // 声明一个静态的实例变量，但不直接创建
    private static volatile SingletonbyLazy singleton;
    // 私有构造函数，防止外部实例化
    private SingletonbyLazy(){};
    // 提供一个全局访问点以获取实例

    /**
     * 双重校验的实现需要考虑到以下几点：
     * 使用 volatile 关键字修饰实例变量，确保可见性。
     * 这是因为实例创建可能涉及到指令重排序，而 volatile 会防止指令重排序，从而保证了线程安全性。
     * 双重校验只有在需要时才进行同步，避免了不必要的性能开销。
     * @return
     */
    public static SingletonbyLazy getSingleton(){
        // 加入双重检查锁定以确保线程安全
        if(singleton == null){
            synchronized (SingletonbyLazy.class){
                if (singleton == null){
                    singleton = new SingletonbyLazy();
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }
        return singleton;
    }
}
