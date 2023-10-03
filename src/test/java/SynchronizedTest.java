import org.junit.Test;

/**
 *  synchronized关键字实现涉及到对象监视器（Monitor），用于实现线程同步
 *  在Java中，每个对象（包括类实例对象，类的类对象）都有一个与之关联的监视器
 *  用于控制对象的访问来确保线程安全（多线程情况下，获取同一个对象监视器锁，才会有同步操作）
 *
 *  对象监视器实现：
 *       Java 对象监视器是通过内置的 monitorenter 和 monitorexit 指令来实现的。这些指令用于在 Java 虚拟机层面进行线程同步和锁管理。
 *       1、当线程进入一个 synchronized 方法或代码块时，Java 虚拟机会使用 monitorenter 指令获取对象的监视器锁
 *       2、当线程退出 synchronized 方法或代码块时，虚拟机会使用 monitorexit 指令释放该对象的监视器锁
 */
public class SynchronizedTest {
    public void nonObjectSync(){
        System.out.println(" nonObjectSync ");
    }
    public synchronized void ObjectSync1() throws InterruptedException {
        System.out.println(" ObjectSync1 ");
        Thread.sleep(1000);
    }
    public synchronized void ObjectSync2(){
        System.out.println(" ObjectSync2 ");
    }

    public static synchronized void classSync1() throws ClassNotFoundException, InterruptedException, NoSuchFieldException {
        System.out.println(" classSync1");
        Thread.sleep(1000);
    }

    public static synchronized void classSync2() throws ClassNotFoundException, NoSuchFieldException {
        System.out.println(" classSync2");
    }


    /**
     * 测试类的类对象监视器同步方法
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        SynchronizedTest synchronizedTest1 = new SynchronizedTest();
        SynchronizedTest synchronizedTest2 = new SynchronizedTest();
        Thread thread1 = new Thread(() -> {
            try {
                synchronizedTest1.classSync1();    // 获取SynchronizedTest.class对象监视器锁
            } catch (InterruptedException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                synchronizedTest2.classSync2();    // 获取SynchronizedTest.class对象监视器锁（阻塞）
            } catch (ClassNotFoundException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join();
    }

    /**
     * 测试类的实例对象监视器同步方法
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        Thread t1 = new Thread(() -> {
            try {
                synchronizedTest.ObjectSync1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            synchronizedTest.ObjectSync2();         // 阻塞
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    /**
     * 测试类同步方法、实例同步方法、非同步方法是否互相影响
     * @throws InterruptedException
     */
    @Test
    public void test3() throws InterruptedException {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        Thread t1 = new Thread(()->{
            try {
                SynchronizedTest.classSync1();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                synchronizedTest.ObjectSync1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            synchronizedTest.nonObjectSync();
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
