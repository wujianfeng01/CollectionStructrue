package DesignMode.TemplateMode;

import JUC.Mutex;

/**
 * 模板实现类
 */
public class  TemplateImplA extends Template{
    Mutex mutex = new Mutex();

    /**
     * template模板类钩子方法tryDoSomething
     * @return
     */
    @Override
    boolean tryDoSomething() {
        System.out.println(Thread.currentThread()+"尝试获取锁....");
        mutex.lock();
        return true;
    }

    /**
     * template模板类钩子方法tryNotDoSomething
     * @return
     */
    @Override
    boolean tryNotDoSomething() {
        System.out.println(Thread.currentThread()+"尝试释放锁....");
        mutex.unLock();
        return true;
    }


}
