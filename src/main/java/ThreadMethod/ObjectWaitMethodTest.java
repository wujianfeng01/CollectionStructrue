package ThreadMethod;
/**
 * wait() 方法是 Object 类中的方法，用于线程之间的同步和协调。
 * 调用 wait() 方法会使当前线程释放锁，并进入等待状态
 * 直到其他线程调用相同对象的 notify() 或 notifyAll() 来唤醒等待的线程。
 * wait() 方法必须在 synchronized 块内使用，因为它需要获取对象的监视器锁。
 * 主要用于线程之间的通信和同步，允许线程等待某个条件满足后再继续执行。
 */
public class ObjectWaitMethodTest {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread is doing some work...");
                try {
                    Thread.sleep(2000); // 模拟线程工作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread finished work.");
                lock.notify(); // 唤醒等待的线程
            }
        });

        synchronized (lock) {
            thread.start();  // 先启动线程，再等待
            try {
                System.out.println("Main thread is waiting for the other thread to finish...");
                lock.wait(); // 主线程等待并被唤醒
                System.out.println("Main thread continues after the other thread finished.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
