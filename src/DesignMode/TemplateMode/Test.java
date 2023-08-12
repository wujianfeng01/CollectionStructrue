package DesignMode.TemplateMode;

/**
 * 模板实现类测试
 */
public class Test {
    public static void main(String[] args) {
        final TemplateImplA templateImplA = new TemplateImplA();
        new Thread(()->{
            templateImplA.tryDoSomething();
            try {
                System.out.println(Thread.currentThread()+"已经获取到锁.....");
                System.out.println(Thread.currentThread()+"开始睡眠.....");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                templateImplA.tryNotDoSomething();
                System.out.println(Thread.currentThread()+"释放锁成功.....");
            }
        }).start();

        new Thread(()->{
            templateImplA.tryDoSomething();
            try {
                System.out.println(Thread.currentThread()+"已经获取到锁.....");
            } finally {
                templateImplA.tryNotDoSomething();
                System.out.println(Thread.currentThread()+"释放锁成功.....");
            }
        }).start();
    }
}
