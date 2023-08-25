package ThreadMethod;

/**
 * sleep() 方法是 Thread 类的静态方法，用于暂停当前线程的执行一段指定的时间。
 * 调用 sleep() 方法不会释放锁，线程仍然持有之前的锁。
 * 主要用于引入时间延迟。
 * sleep() 方法的调用过程是静态的，不需要在同步块中使用。
 */
public class ThreadSleepMethodTest {
    public static void main(String[] args) {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread is doing some work...");
                try {
                    Thread.sleep(1000); // 模拟线程工作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread finished work.");
            }
        });

        synchronized (lock) {
            thread.start();  // 先启动线程
            try {
                System.out.println("Main thread is waiting for the other thread to finish...");
                Thread.sleep(1000);
                System.out.println("Main thread continues after the other thread finished.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
